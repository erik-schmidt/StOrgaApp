import React from "react";
import styles from "./index.style";
import { View } from "react-native";
import CourseList from "./CourseList/CourseList";

const CourseScreen = () => {
  return (
    <View style={styles.container}>
      <CourseList />
    </View>
  );
};

export default CourseScreen;
