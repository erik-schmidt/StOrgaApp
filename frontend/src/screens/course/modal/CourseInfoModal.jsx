import React, { useState } from "react";
import styles from "./CourseInfoModal.style";
import { View, Text, Button } from "react-native";

const CourseInfoModal = () => {
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
      <Button title="Delete Course" onPress={() => alert("Kurs gelöscht")} />
    </View>
  );
};

export default CourseInfoModal;
