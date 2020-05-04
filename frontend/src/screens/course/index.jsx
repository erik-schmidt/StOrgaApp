import React from "react";
import styles from "./index.style";
import { View, Platform } from "react-native";
import CourseList from "./CourseList/CourseList";

const CourseScreen = ({ navigation }) => {
  return (
    <View style={styles.container}>
      <CourseList />
    </View>
  );
};

export default CourseScreen;
