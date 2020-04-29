import React, { useState } from "react";
import styles from "./CourseInformationModal.style";
import { View, Text } from "react-native";
import { FontAwesome5 } from "@expo/vector-icons";
import { deleteCourse } from "../../../api/services/courseService";
import Toast from "../../../components/Toast/Toast";

const CourseInfoModal = ({ navigation, route }) => {
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

  const onDeleteCourse = () => {
    deleteCourse().then((res) => {
      setVisible(true);
      setTimeout(() => {
        setVisible(false);
        navigation.navigate("Fächer", { deleteCourse: true });
      }, 3000);
    });
  };

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Veranstaltung: {course.description}</Text>
      <Text style={styles.text}>Professor: {course.professor}</Text>
      <Text style={styles.text}>ECTS: {course.ects}</Text>
      <Text style={styles.text}>
        Empfohlenes Semester: {course.requiredSemester}
      </Text>
      <Toast
        color="green"
        text="Kurs wurde erfolgreich gelöscht"
        showModal={visible}
      />
    </View>
  );
};

export default CourseInfoModal;
