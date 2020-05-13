import React, { useState, useEffect } from "react";
import styles from "./index.style";
import { View } from "react-native";
import CourseList from "./CourseList/CourseList";

const CourseScreen = ({ navigation }) => {
  return (
    <View style={styles.container}>
      <CourseList />
    </View>
  );
};

export default CourseScreen;
