import React from "react";
import { View } from "react-native";
import LinkList from "./LinkList/LinkList";
import styles from "./index.style";

const LinkScreen = () => {
  return (
    <View style={styles.container}>
      <LinkList />
    </View>
  );
};

export default LinkScreen;
