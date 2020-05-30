import React, { useState, useEffect } from "react";
import { View, Picker } from "react-native";
import { getAllCourses } from "../../../api/services/CourseService";
import styles from "./AddCourseModal.style";
import Toast from "../../../components/Toast/Toast";
import AppModal from "../../../components/AppModal/AppModal";
import AppButton from "../../../components/AppButton/AppButton";
import * as HttpStatus from 'http-status-code';

const AddCourseModal = ({ navigation }) => {
  const [courses, setCourses] = useState([]);
  const [selectedCourse, setSelectedCourse] = useState();
  const [visible, setVisible] = useState(false);

  useEffect(() => {
    getAllCourses()
      .then((res) => {
        console.log(res);
        if (res.status === HttpStatus.OK) {
          setCourses(res.data);
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
          alert(err);
      });
  }, []);

  return (
    <View style={styles.container}>
      <AppModal header="Kurs zur Liste hinzufügen" height={280} width={280}>
        <Picker
          selectedValue={""}
          style={styles.picker}
          onValueChange={(itemValue, itemIndex) => {
            setSelectedCourse(itemValue);
          }}
        >
          {courses.map((course) => {
            return <Picker.Item label={course.description} value={course} />;
          })}
        </Picker>
        <AppButton
        onPress={() => console.log("Speichern ausgewählt")}
          text="Speichern"
      />
        <AppButton onPress={() => navigation.pop()} text="Abbrechen" />
      </AppModal>
   </View>
  );
};

export default AddCourseModal;
