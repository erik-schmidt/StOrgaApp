import React, { useState, useEffect, useCallback } from "react";
import { View, FlatList, Linking, Text, TouchableOpacity } from "react-native";
import { getAllLinks } from "../../../api/services/LinkService";
import Toast from "../../../components/Toast/Toast";
import styles from "./LinkList.style";

const LinkList = () => {
  const [links, setLinks] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    getAllLinks()
      .then((res) => {
        if (res != undefined) {
          setLinks(res.data);
        } else {
          throw new Error();
        }
      })
      .catch((err) => {
        setErrorMessage("Keine Verbindung zum Server");
        setShowModal(true);
        setTimeout(() => {
          setShowModal(false);
        }, 3000);
      });
  }, []);

  const OpenLinkCard = ({ url, children, description }) => {
    let link = url;
    if (!url.startsWith("http://") || !url.startsWith("https://")) {
      link = "https://" + url;
    }
    const handlePress = useCallback(async () => {
      const supported = await Linking.canOpenURL(link);

      if (supported) {
        await Linking.openURL(link);
      } else {
        setErrorMessage(`Don't know how to open this URL: ${link}`);
        setShowModal(true);
        setTimeout(() => {
          setShowModal(false);
        }, 2500);
      }
    }, [url]);
    return (
      <TouchableOpacity onPress={handlePress}>
        <View>
          <Text style={styles.linkText}>{children}</Text>
          <Text style={styles.descriptionText}>{description}</Text>
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
                }}
              >
                Keine Links bisher gespeichert.
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
          <OpenLinkCard description={item.linkDescription} url={item.link}>
            {item.link}
          </OpenLinkCard>
        )}
        keyExtractor={(item) => item.id}
      />
      <Toast showModal={showModal} color="red" text={errorMessage} />
    </View>
  );
};

export default LinkList;
