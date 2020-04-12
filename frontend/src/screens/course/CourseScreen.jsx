import React, { useState, useEffect, useLayoutEffect } from "react";
import styles from "./CourseScreen.style";
import { Modal, TouchableHighlight, Text, View, Alert } from "react-native";
import BottomButton from "../../components/BottomButton/BottomButton";
import InputModal from "../../components/InputModal/InputModal";

const CourseScreen = ({ navigation }) => {
  const [courses, setCourses] = useState([]);
  const [course, setCourse] = useState("");
  const [modalVisible, setModalVisible] = useState(false);

  useLayoutEffect(() => {}, []);

  useEffect(() => {
    console.log("Current Courses", courses);
  }, [courses]);

  const saveContent = (content) => {
    setCourse(content);
    const addCourse = [...courses];
    addCourse.push(course);
    setCourses(addCourse);
  };

  return (
    <View style={styles.container}>
      {modalVisible ? (
        <InputModal
          isOpen={modalVisible}
          closeModal={() => setModalVisible(false)}
          saveContent={saveContent}
          heading="Kurs hinzufügen"
        />
      ) : null}
      <View style={styles.centeredView}>
        {courses.map((course) => {
          return <Text>{course}</Text>;
        })}
      </View>
      <BottomButton
        onPress={() => setModalVisible(true)}
        text="Kurs hinzufügen"
      />
    </View>
  );
};

export default CourseScreen;
