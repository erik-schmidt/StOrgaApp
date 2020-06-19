import React, { useState, useEffect, useCallback } from "react";
import { View, FlatList, Linking, Text, TouchableOpacity } from "react-native";
import { getAllLinks } from "../../../api/services/LinkService";
import styles from "./LinkList.style";
import { useNavigation, useRoute } from "@react-navigation/native";
import * as HttpStatus from "http-status-codes";

const LinkList = () => {
  const navigation = useNavigation();
  const route = useRoute();
  const [links, setLinks] = useState([]);

  useEffect(() => {
    getAllLinks()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setLinks(res.data);
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
  }, []);

  useEffect(() => {
    getAllLinks()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setLinks(res.data);
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
  }, [route]);

  const OpenLinkCard = ({ id, link, linkDescription }) => {
    const handlePress = useCallback(async () => {
      const supported = await Linking.canOpenURL(link);

      if (supported) {
        await Linking.openURL(link);
      } else {
        alert(`Don't know how to open this URL: ${link}`);
      }
    }, [link]);
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
                  justifyContent: "center",
                  alignSelf: "center",
                  fontSize: 20,
                  fontWeight: "bold",
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
