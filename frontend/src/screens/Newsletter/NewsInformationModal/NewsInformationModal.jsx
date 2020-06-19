import React, { useState } from "react";
import styles from "./NewsInformationModal.style";
import { View, Text } from "react-native";
import "moment/locale/de";

const NewsInformationModal = ({ navigation, route }) => {
  const [news, setNews] = useState(route.params?.news);

  return (
    <View style={styles.container}>
      <Text style={styles.header}>{news.title}</Text>
      <Text style={styles.text}>Erschienen : {news.published} </Text>
      <Text style={styles.text}>Author : {news.author}</Text>
      <Text style={styles.text}>{news.message} </Text>
    </View>
  );
};

export default NewsInformationModal;
