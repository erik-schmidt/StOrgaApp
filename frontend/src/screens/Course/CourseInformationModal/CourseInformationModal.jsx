import React, { useState } from "react";
import styles from "./CourseInformationModal.style";
import { View, Text } from "react-native";
import { FontAwesome5 } from "@expo/vector-icons";

const CourseInformationModal = ({ navigation, route }) => {
  navigation.setOptions({
    headerRight: () => (
      <FontAwesome5.Button
        name="edit"
        color="black"
        backgroundColor="#ffff"
        onPress={() =>
          navigation.navigate("CourseMenu", {
            editMode: true,
            course: course,
          })
        }
      />
    ),
  });
  const [course, setCourse] = useState(route.params?.course);

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Veranstaltung: {course.description}</Text>
      <Text style={styles.text}>Studiengang: {course.fieldOfStudy}</Text>
      <Text style={styles.text}>Professor: {course.professor}</Text>
      <Text style={styles.text}>Raum: {course.room}</Text>
      <Text style={styles.text}>
        Wahlpflicht-/Pflichtfach: {course.kindOfSubject}
      </Text>
      <Text style={styles.text}>Vertiefungsrichtung {course.studyFocus}</Text>
      <Text style={styles.text}>ECTS: {course.ects}</Text>
      <Text style={styles.text}>
        Empfohlenes Semester: {course.recommendedSemester}
      </Text>
      <Text style={styles.text}>
        Kontaktstunden: {course.workingHoursInClass}
      </Text>
      <Text style={styles.text}>Selbststudium: {course.workingHoursSelf}</Text>
      <Text style={styles.text}>Prüfungsart: {course.kindOfExam}</Text>
    </View>
  );
};

export default CourseInformationModal;
