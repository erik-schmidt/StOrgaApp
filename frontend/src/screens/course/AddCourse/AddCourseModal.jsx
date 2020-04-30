import React, { useState, useEffect } from "react";
import { View, Text, TouchableHighlight } from "react-native";
import { Picker } from "@react-native-community/picker";
import { getAllCourses } from "../../../api/services/courseService";
import styles from "./AddCourseModal.style";
import Toast from "../../../components/Toast/Toast";

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
      <View style={styles.modalView}>
        <Text style={styles.headerText}>
          Füge ein Kurs in deine Liste hinzu:
        </Text>
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
        <TouchableHighlight
          style={styles.modalButton}
          onPress={() => console.log("Speichern wurde ausgewählt")}
        >
          <Text style={styles.textStyle}>Speicher</Text>
        </TouchableHighlight>
        <TouchableHighlight
          style={styles.modalButton}
          onPress={() => navigation.pop()}
        >
          <Text style={styles.textStyle}>Abbrechen</Text>
        </TouchableHighlight>
      </View>
      <Toast
        color="red"
        showModal={visible}
        text="Keine Verbindung zum Server"
      />
    </View>
  );
};

export default AddCourseModal;
