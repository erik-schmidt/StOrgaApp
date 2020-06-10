import React from "react";
import styles from "./index.style";
import { View } from "react-native";
import NewsList from "./NewsletterList/NewsletterList";

const NewsletterScreen = ({ navigation }) => {
  return (
    <View style={styles.container}>
      <NewsList />
    </View>
  );
};

export default NewsletterScreen;
