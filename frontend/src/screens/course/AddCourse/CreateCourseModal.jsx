import React, { useState } from "react";
import { View, Button, Platform, Picker } from "react-native";
import { TextInput, TouchableOpacity } from "react-native-gesture-handler";
import styles from "./CreateCourseModal.style";
import Course from "../../../models/course";
import { createCourse } from "../../../api/services/courseService";

const CreateCourseModal = ({ navigation }) => {
  const [description, setDescription] = useState("");
  const [professor, setProfessor] = useState("");
  const [room, setRoom] = useState("");
  const [ects, setEcts] = useState();
  const [grade, setGrade] = useState(null);
  const [fieldOfStudy, setFieldOfStudy] = useState("");

  const saveContent = () => {
    if (
      description.trim() &&
      professor.trim() &&
      room.trim() &&
      ects.trim() &&
      fieldOfStudy.trim()
    ) {
      const course = new Course(
        description,
        professor,
        room,
        ects,
        grade,
        fieldOfStudy
      );
      createCourse(course).then((res) => {
        console.log(res);
        navigation.navigate("Fächer", { request: true });
      });
    } else {
      alert("Bitte fülle alle Felder aus");
    }
  };

  const onStartTimeChange = (event, selectedTime) => {
    setShowStart(Platform.OS === "ios") && setShowEnd(Platform.OS === "ios");
    if (event.type === "set") {
      setStartTime(selectedTime.toLocaleTimeString("de-DE").slice(0, 5));
    }
  };

  const onEndTimeChange = (event, selectedTime) => {
    setShowEnd(Platform.OS === "ios") && setShowStart(Platform.OS === "ios");
    if (event.type === "set") {
      setEndTime(selectedTime.toLocaleTimeString("de-DE").slice(0, 5));
    }
  };

  return (
    <View style={styles.container}>
      <TextInput
        style={styles.textInput}
        placeholder="Kursname"
        onChangeText={(text) => setDescription(text)}
        defaultValue={description}
      />
      <TextInput
        style={styles.textInput}
        placeholder="Professor"
        onChangeText={(text) => setProfessor(text)}
        defaultValue={professor}
      />
      <TextInput
        style={styles.textInput}
        placeholder="Raum"
        onChangeText={(text) => setRoom(text)}
        defaultValue={room}
      />
      <TextInput
        style={styles.textInput}
        placeholder="ECTS"
        onChangeText={(text) => setEcts(text)}
        defaultValue={ects}
        keyboardType="numeric"
      />
      <TextInput
        style={styles.textInput}
        placeholder="Note"
        onChangeText={(text) => setGrade(text)}
        keyboardType="numeric"
        defaultValue={grade}
      />
      <TextInput
        style={styles.textInput}
        placeholder="Studiengang"
        onChangeText={(text) => setFieldOfStudy(text)}
        defaultValue={fieldOfStudy}
      />
      <Button
        onPress={() => {
          console.log(description);
          saveContent();
        }}
        title="Speichern"
      />
    </View>
  );
};

export default CreateCourseModal;
