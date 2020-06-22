import React, { useState, useEffect, useCallback } from "react";
import { View, FlatList, Linking, Text, TouchableOpacity } from "react-native";
import { getAllLinks } from "../../../api/services/LinkService";
import styles from "./LinkList.style";
import { useNavigation } from "@react-navigation/native";
import * as HttpStatus from "http-status-codes";
import { useFocusEffect } from "@react-navigation/native";
import AuthContext from "../../../constants/AuthContext";

const LinkList = () => {
  const navigation = useNavigation();
  const [links, setLinks] = useState([]);
  const { signOut } = React.useContext(AuthContext);

  useFocusEffect(
    React.useCallback(() => {
      getAllLinks()
        .then((res) => {
          if (res.status === HttpStatus.OK) {
            setLinks(res.data);
          } else if (res.status === HttpStatus.UNAUTHORIZED) {
            signOut();
          } else {
            throw new Error(res.data);
          }
        })
        .catch((err) => {
          alert(err);
        });
    })
  );

  useEffect(() => {
    getAllLinks()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setLinks(res.data);
        } else if (res.status === HttpStatus.UNAUTHORIZED) {
          signOut();
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
  }, []);

  const OpenLinkCard = ({ id, link, linkDescription }) => {
    let url = link;
    const handlePress = useCallback(async () => {
      if (!link.includes("http://") || !link.includes("https://")) {
        url = "https://" + link;
      }
      const supported = await Linking.canOpenURL(url);

      if (supported) {
        await Linking.openURL(url);
      } else {
        alert(`Don't know how to open this URL: ${url}`);
      }
    }, [url]);
    return (
      <TouchableOpacity
        onPress={handlePress}
        onLongPress={() =>
          navigation.navigate("LinkMenu", {
            link: { id, link, linkDescription },
          })
        }
      >
        <View>
          <Text style={styles.descriptionText}>{linkDescription}</Text>
          <Text style={styles.linkText}>{link}</Text>
        </View>
      </TouchableOpacity>
    );
  };

  return (
    <View style={styles.container}>
      <FlatList
        data={links}
        ListEmptyComponent={() => {
          return (
            <View style={styles.container}>
              <Text
                style={{
                  textAlign: "center",
                  fontSize: 20,
                  fontWeight: "bold",
                  marginTop: "80%",
                  color: "#66CDAA",
                }}
              >
                Keine Links gespeichert.
              </Text>
            </View>
          );
        }}
        ItemSeparatorComponent={() => (
          <View
            style={{
              width: "95%",
              height: 1,
              borderBottomWidth: 1,
              borderBottomColor: "grey",
              margin: 10,
            }}
          />
        )}
        renderItem={({ item }) => (
          <OpenLinkCard
            id={item.id}
            item={item}
            link={item.link}
            linkDescription={item.linkDescription}
          />
        )}
        keyExtractor={(item) => item.id}
      />
    </View>
  );
};

export default LinkList;
