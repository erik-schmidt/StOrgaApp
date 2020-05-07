import React, { useState, useEffect } from "react";
import { View, Picker } from "react-native";
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
        <View style={{ marginBottom: 25 }}>
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
            onPress={() =>
              navigation.navigate("CourseList", { course: selectedCourse })
            }
            text="Speichern"
          />
        </View>
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
