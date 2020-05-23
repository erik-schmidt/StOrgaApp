import React, { useState } from "react";
import { View, TextInput } from "react-native";
import styles from "./CourseMenu.style";
import AppButton from "../../../components/AppButton/AppButton";
import { deleteCourse } from "../../../api/services/CourseService";
import Toast from "../../../components/Toast/Toast";
import AppModal from "../../../components/AppModal/AppModal";
import * as HttpStatus from "http-status-codes";

const CourseMenu = ({ navigation, route }) => {
  const [course, setCourse] = useState(route.params?.course);
  const [editMode, setEditMode] = useState(route.parms?.editMode);
  const [selectedGrade, setSelectedGrade] = useState();

  const onDeleteCourse = () => {
    deleteCourse(course.number).then(res => {
      if (res.status === HttpStatus.OK) {
        navigation.navigate("Fächer", {courseDeleted: true});
      } else {
        throw new Error(res.data);
      }
    }).catch(err => {
      alert(err);
    })
  };

  const onChangeGrade = () => {
    setEditMode(true);
  };

  return (
    <View style={styles.container}>
      {editMode ? (
        <View>
          <AppModal header="Note eintragen" description={course.description}>
            <TextInput
              style={styles.textInput}
              keyboardType="number-pad"
              placeholder="Note"
              onChangeText={(text) => setSelectedGrade(text)}
              value={selectedGrade}
            />
            <AppButton
              onPress={() => console.log("Ändern ausgewählt")}
              text="Speichern"
            />
            <AppButton onPress={() => navigation.pop()} text="Abbrechen" />
          </AppModal>
        </View>
      ) : (
        <View>
          <AppModal header="Veranstaltung:" description={course.description}>
            <AppButton onPress={() => onChangeGrade()} text="Note ändern" />
            <AppButton
              color="red"
              onPress={() => onDeleteCourse()}
              text="Kurs löschen"
            />
            <AppButton onPress={() => navigation.pop()} text="Abbrechen" />
          </AppModal>
        </View>
      )}
    </View>
  );
};

export default CourseMenu;
