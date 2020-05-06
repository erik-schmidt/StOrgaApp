import React, { useState, useEffect } from "react";
import { View, Picker } from "react-native";
<<<<<<< HEAD
//import { Picker } from "./node_modules/@react-native-community/picker";
=======
// import { Picker } from "@react-native-community/picker";
>>>>>>> 0cdb888c1ce63667823909328802459c6a4e7ca2
import { getAllCourses } from "../../../api/services/CourseService";
import styles from "./AddCourseModal.style";
import Toast from "../../../components/Toast/Toast";
import AppModal from "../../../components/AppModal/AppModal";
import AppButton from "../../../components/AppButton/AppButton";

const AddCourseModal = ({ navigation }) => {
  const [courses, setCourses] = useState([]);
  const [selectedCourse, setSelectedCourse] = useState();
  const [visible, setVisible] = useState(false);

  useEffect(() => {
    getAllCourses()
      .then((res) => {
        console.log(res);
        if (res != undefined) {
          setCourses(res.data);
        } else {
          throw new Error();
        }
      })
      .catch((err) => {
        setShowModal(true);
        setTimeout(() => {
          setShowModal(false);
        }, 5000);
      });
  }, []);

  return (
    <View style={styles.container}>
      <AppModal header="Kurs zur Liste hinzufügen">
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
      <Toast
        color="red"
        showModal={visible}
        text="Keine Verbindung zum Server"
      />
    </View>
  );
};

export default AddCourseModal;
