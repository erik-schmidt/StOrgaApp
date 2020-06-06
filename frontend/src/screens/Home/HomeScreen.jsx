import React from "react";
import { Text, View, Button } from "react-native";
import styles from "./HomeScreen.style";

const HomeScreen = ({ navigation }) => {
  return (
    <View style={styles.container}>
      <Text>Willkommen bei StOrga!</Text>
    </View>
  );
};

export default HomeScreen;
