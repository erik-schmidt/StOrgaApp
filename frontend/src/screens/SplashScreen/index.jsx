import React from "react";
import styles from "./index.styles";
import { ActivityIndicator, View, Text } from "react-native";

const SplashScreen = () => {
  return (
    <View styles={styles.container}>
      <ActivityIndicator
        style={{
          alignSelf: "center",
          marginTop: 400,
          marginBottom: 10,
        }}
        size="large"
        color="#2196F3"
      />
      <Text style={{ textAlign: "center", fontSize: 15 }}>Loading...</Text>
    </View>
  );
};

export default SplashScreen;
