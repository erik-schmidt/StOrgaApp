import React, { useState, useEffect } from "react";
import {
  Text,
  View,
  Modal,
  TouchableHighlight,
  TouchableOpacity,
} from "react-native";
import {
  getAllCourses,
  deleteCourse,
} from "../../../api/services/courseService";
import { FlatList } from "react-native-gesture-handler";
import Card from "../../../components/CardList/Card";
import styles from "./CourseList.style";

const CourseList = (props) => {
  const [courses, setCourses] = useState([]);
  const [visible, setVisible] = useState(false);
  const [selectedCourse, setSelectedCourse] = useState("");

  const openModal = (description) => {
    setVisible(!visible);
    setSelectedCourse(description);
  };

  const closeModal = () => {
    setVisible(!visible);
  };

  const onDeletePress = () => {
    const id = courses.findIndex(
      (course) => course.description === selectedCourse
    );
    deleteCourse(id).then((res) => {
      const deletedCourse = res;
      setCourses(courses.filter((course) => course !== deletedCourse));
    });
  };

  useEffect(() => {
    getAllCourses().then((res) => {
      if (res != undefined) {
        setCourses(res.data);
      }
    });
  }, []);

  return (
    <View>
      <FlatList
        data={courses}
        renderItem={({ item }) => (
          <Card
            key={courses.length}
            onLongPress={() => openModal(item.description)}
            onPress={props.onPress}
            modal={visible}
          >
            <View style={styles.cardText}>
              <Text style={styles.courseHeader}>Veranstaltung: </Text>
              <Text style={styles.courseDescription}>{item.description}</Text>
            </View>
            <View style={styles.cardText}>
              <Text style={styles.boldText}>ECTS: </Text>
              <Text>{item.ects}</Text>
            </View>
            <View style={styles.cardText}>
              <Text style={styles.boldText}>Empfohlenes Semester: </Text>
              <Text>{item.requiredSemester}</Text>
            </View>
          </Card>
        )}
        keyExtractor={(item) => item.description}
      />
      <Modal
        animationType="slide"
        transparent={true}
        visible={visible}
        onRequestClose={() => {
          setVisible(!visible);
        }}
      >
        <View style={styles.centeredView}>
          <View style={styles.modalView}>
            <Text
              style={{ ...styles.modalText, fontWeight: "bold", fontSize: 20 }}
            >
              Veranstaltung: {selectedCourse}
            </Text>
            <TouchableHighlight
              style={styles.modalButton}
              onPress={() => console.log("Ändern wurde ausgewählt")}
            >
              <Text style={styles.textStyle}>Note ändern</Text>
            </TouchableHighlight>
            <TouchableHighlight
              style={{ ...styles.modalButton, backgroundColor: "#f00" }}
              onPress={() => onDeletePress()}
            >
              <Text style={styles.textStyle}>Löschen</Text>
            </TouchableHighlight>
            <TouchableHighlight
              style={{ ...styles.modalButton, marginTop: 25 }}
              onPress={() => closeModal()}
            >
              <Text style={styles.textStyle}>Abbrechen</Text>
            </TouchableHighlight>
          </View>
        </View>
      </Modal>
    </View>
  );
};

export default CourseList;
