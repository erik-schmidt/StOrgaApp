import React, { useState } from "react";
import styles from "./CourseInformationModal.style";
import { View, Text } from "react-native";
import { FontAwesome5 } from "@expo/vector-icons";
import { deleteCourse } from "../../../api/services/courseService";
import Toast from "../../../components/Toast/Toast";

const CourseInformationModal = ({ navigation, route }) => {
  navigation.setOptions({
    headerRight: () => (
      <View style={{ flexDirection: "row" }}>
        <FontAwesome5.Button
          name="trash"
          color="black"
          backgroundColor="#ffff"
          onPress={() => onDeleteCourse()}
        />
        <FontAwesome5.Button
          name="edit"
          color="black"
          backgroundColor="#ffff"
        />
      </View>
    ),
  });
  const [course, setCourse] = useState(route.params?.course);
  const [visible, setVisible] = useState(false);
  const [error, setError] = useState(false);

  const onDeleteCourse = () => {
    deleteCourse()
      .then((res) => {
        if (res != undefined) {
          setVisible(true);
          setTimeout(() => {
            setVisible(false);
            navigation.navigate("Fächer", { deleteCourse: true });
          }, 3000);
        } else {
          throw new Error();
        }
      })
      .catch((err) => {
        setError(true);
        setTimeout(() => {
          setError(false);
        }, 3000);
      });
  };

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
        Empfohlenes Semester: {course.requiredSemester}
      </Text>
      <Toast
        color="green"
        text="Kurs wurde erfolgreich gelöscht"
        showModal={visible}
      />
      <Toast color="red" text="Keine Verbindung zum Server" showModal={error} />
    </View>
  );
};

export default CourseInformationModal;
