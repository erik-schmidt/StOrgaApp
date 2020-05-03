import React, { useState } from "react";
import { View, Text, TouchableHighlight } from "react-native";
import styles from "./CourseMenu.style";
import AppButton from "../../../components/AppButton/AppButton";
import { deleteCourse } from "../../../api/services/courseService";
import Toast from "../../../components/Toast/Toast";
import AppModal from "../../../components/AppModal/AppModal";

const CourseMenu = ({ navigation, route }) => {
  const [course, setCourse] = useState(route.params?.course);
  const [error, setError] = useState(false);
  const [visible, setVisible] = useState(false);

  const onDeleteCourse = () => {
    deleteCourse(course.number)
      .then((res) => {
        if (res != undefined) {
          setVisible(true);
          setTimeout(() => {
            setVisible(false);
            navigation.navigate("Fächer", { deleteCourse: true });
          }, 1000);
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
      <AppModal header="Veranstaltung:" description={course.description}>
        <AppButton
          onPress={() => console.log("Ändern ausgewählt")}
          text="Note ändern"
        />
        <AppButton
          color="red"
          onPress={() => onDeleteCourse()}
          text="Kurs löschen"
        />
        <AppButton onPress={() => navigation.pop()} text="Abbrechen" />
      </AppModal>
      <Toast color="red" showModal={error} text="Keine Verbindung zum Server" />
      <Toast
        color="green"
        showModal={visible}
        text="Kurs erfolgreich gelöscht"
      />
    </View>
  );
};

export default CourseMenu;
