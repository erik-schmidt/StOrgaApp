import React, { useState, useEffect } from "react";
import styles from "./CourseScreen.style";
import { Text, View } from "react-native";
import AddButton from "../../components/AddButton/AddButton";

const CourseScreen = ({ route }) => {
  const [courses, setCourses] = useState([]);
  const [course, setCourse] = useState(route.params?.courseName);

  useEffect(() => {}, []);

  useEffect(() => {
    console.log("Current Courses", courses);
  }, [courses, route.params?.courseName]);

  return (
    <View style={styles.container}>
      <View style={styles.centeredView}>
        <Text>{route.params?.courseName}</Text>
      </View>
    </View>
  );
};

export default CourseScreen;
