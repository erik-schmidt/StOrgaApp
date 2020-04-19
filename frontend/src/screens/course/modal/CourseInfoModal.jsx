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
    <View>
      <Text>Veranstaltung:</Text>
      <Text>{course.description}</Text>
      <Text>Professor:</Text>
      <Text>{course.professor}</Text>
      <Text>ECTS:</Text>
      <Text>{course.ects}</Text>
      <Text>Empfohlenes Semester</Text>
      <Text>{course.requiredSemester}</Text>
      <Button title="Delete Course" onPress={() => alert("Kurs gelöscht")} />
    </View>
  );
};

export default CourseInfoModal;
