import React, { useState } from "react";
import { View, TextInput } from "react-native";
import styles from "./CourseMenu.style";
import AppButton from "../../../components/AppButton/AppButton";
import { deleteCourse } from "../../../api/services/CourseService";
import { addGrade } from "../../../api/services/GradeService";
import AppModal from "../../../components/AppModal/AppModal";
import * as HttpStatus from "http-status-codes";
import AuthContext from "../../../constants/AuthContext";

const CourseMenu = ({ navigation, route }) => {
  const [course, setCourse] = useState(route.params?.course);
  const [editMode, setEditMode] = useState(route.parms?.editMode);
  const [selectedGrade, setSelectedGrade] = useState();
  const { signOut } = React.useContext(AuthContext);

  const onDeleteCourse = () => {
    deleteCourse(course.number)
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          navigation.navigate("Fächer");
        } else if (res.status === HttpStatus.UNAUTHORIZED) {
          signOut();
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
  };

  const onChangeGrade = () => {
    let changedGrade = selectedGrade;
    if (selectedGrade.includes(",")) {
      changedGrade = selectedGrade.replace(",", ".");
    }
    addGrade({
      courseName: course.description,
      courseNumber: course.number,
      grade: changedGrade,
    })
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          navigation.navigate("Fächer");
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
  };

  return (
    <View style={styles.container}>
      {editMode ? (
        <View>
          <AppModal
            header="Note eintragen"
            description={course.description}
            width={250}
            height={300}
          >
            <TextInput
              style={styles.textInput}
              keyboardType="number-pad"
              placeholder="Note"
              onChangeText={(text) => setSelectedGrade(text)}
              value={selectedGrade}
            />
            <AppButton onPress={() => onChangeGrade()} text="Speichern" />
            <AppButton onPress={() => navigation.pop()} text="Abbrechen" />
          </AppModal>
        </View>
      ) : (
        <View>
          <AppModal
            header="Veranstaltung:"
            description={course.description}
            width={250}
            height={300}
          >
            <AppButton onPress={() => setEditMode(true)} text="Note ändern" />
            <AppButton
              color="red"
              onPress={() => onDeleteCourse()}
              text="Vom Kurs austreten"
            />
            <AppButton onPress={() => navigation.pop()} text="Abbrechen" />
          </AppModal>
        </View>
      )}
    </View>
  );
};

export default CourseMenu;
