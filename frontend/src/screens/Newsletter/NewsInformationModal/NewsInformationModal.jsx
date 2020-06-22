import React, { useState, useCallback } from "react";
import styles from "./NewsInformationModal.style";
import { View, Text, TouchableOpacity, Linking } from "react-native";
import "moment/locale/de";

const NewsInformationModal = ({ route }) => {
  const [news, setNews] = useState(route.params?.news);

  const OpenLinkCard = ({ link }) => {
    let url = link;

    const handlePress = useCallback(async () => {
      await Linking.openURL(url);
    }, [url]);
    return (
      <TouchableOpacity onPress={handlePress}>
        <Text style={styles.linkText}>{link} </Text>
      </TouchableOpacity>
    );
  };

  return (
    <View style={styles.container}>
      <Text style={styles.header}>{news.title}</Text>
      <Text style={styles.text}>Erschienen: {news.published} </Text>
      <Text style={styles.text}>Author: {news.author}</Text>
      <Text style={styles.text}>{news.message} </Text>
      <Text style={styles.text}>mehr dazu hier: </Text>
      <OpenLinkCard link={news.urlLink} />
    </View>
  );
};

export default NewsInformationModal;
