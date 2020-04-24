import React, { useState } from "react";
import styles from "./CourseInformationModal.style";
import { View, Text, Button } from "react-native";
import { FontAwesome5 } from "@expo/vector-icons";

const CourseInfoModal = ({ navigation }) => {
  navigation.setOptions({
    headerRight: () => (
      <View style={{ flexDirection: "row" }}>
        <FontAwesome5.Button
          name="trash"
          color="black"
          backgroundColor="#ffff"
        />
        <FontAwesome5.Button
          name="edit"
          color="black"
          backgroundColor="#ffff"
        />
      </View>
    ),
  });
  const [course, setCourse] = useState({
    description: "Test",
    professor: "Haag",
    ects: 5,
    requiredSemester: 4,
  });

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Veranstaltung: {course.description}</Text>
      <Text style={styles.text}>Professor: {course.professor}</Text>
      <Text style={styles.text}>ECTS: {course.ects}</Text>
      <Text style={styles.text}>
        Empfohlenes Semester: {course.requiredSemester}
      </Text>
    </View>
  );
};

export default CourseInfoModal;
