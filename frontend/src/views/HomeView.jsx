import React from "react";
import { Text, View, Button } from "react-native";
import styles from "../styles/HomeView.style";

const HomeView = ({ navigation }) => {
  return (
    <View style={styles.container}>
      <Text>Hello there! What uuuuuuuuuup?</Text>
      <Button
        title="zum Kalender"
        onPress={() => navigation.navigate("Kalender")}
      />
    </View>
  );
};

export default HomeView;
