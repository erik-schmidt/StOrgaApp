import React from "react";
import styles from "./index.style";
import GradeList from "./GradeList/GradeList";
import { View } from "react-native";

const GradeScreen = () => {
  return (
    <View style={styles.container}>
      <GradeList />
    </View>
  );
};

export default GradeScreen;

