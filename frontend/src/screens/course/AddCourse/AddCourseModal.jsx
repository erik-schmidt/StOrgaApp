import React, { useState, useEffect } from "react";
import { View, Text, TouchableHighlight } from "react-native";
import { Picker } from "@react-native-community/picker";
import { getAllCourses } from "../../../api/services/courseService";
import styles from "./AddCourseModal.style";

const AddCourseModal = ({ navigation }) => {
  const [courses, setCourses] = useState([]);
  const [selectedCourse, setSelectedCourse] = useState();

  useEffect(() => {
    getAllCourses().then((res) => {
      if (res != undefined) {
        setCourses(res.data);
      }
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
    </View>
  );
};

export default AddCourseModal;
