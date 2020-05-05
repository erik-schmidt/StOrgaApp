import React from "react";
import styles from "./index.styles";
import { ActivityIndicator, View, Text } from "react-native";

const SplashScreen = () => {
  return (
    <View styles={styles.container}>
      <ActivityIndicator size="large" color="#2196F3" />
      <Text>Loading...</Text>
    </View>
  );
};

export default SplashScreen;
