import React, { useState } from "react";
import { Text, View, SectionList } from "react-native";
import styles from "./HomeScreen.style";

const HomeScreen = ({ navigation }) => {
  const [latestItems, setLatestItems] = useState([]);
  return (
    <View style={styles.container}>
      <SectionList />
    </View>
  );
};

export default HomeScreen;
