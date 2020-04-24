import React from "react";
import styles from "./index.style";
import { View, Platform } from "react-native";
import CourseTable from "./CourseTable/CourseTable";
import CourseList from "./CourseList/CourseList";

const CourseScreen = ({ navigation }) => {
  return (
    <View style={styles.container}>
      {Platform.OS !== "web" ? (
        <CourseList
          onPress={() => navigation.navigate("CourseInformationModal")}
        />
      ) : (
        <CourseTable />
      )}
    </View>
  );
};

export default CourseScreen;
