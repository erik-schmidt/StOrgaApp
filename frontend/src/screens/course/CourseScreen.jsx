import React, { useState, useEffect } from "react";
import styles from "./CourseScreen.style";
import { Text, View, Platform, Alert } from "react-native";
import { FlatList } from "react-native-gesture-handler";
import Card from "../../components/CardList/Card";
import { getAllCourses } from "../../api/services/courseService";
import { FontAwesome5 } from "@expo/vector-icons";

const CourseScreen = ({ navigation, route }) => {
  const [courses, setCourses] = useState([
    { description: "Test", professor: "Haag", ects: 5, requiredSemester: 4 },
  ]);

  useEffect(() => {
    getAllCourses().then((res) => {
      if (res !== undefined) {
        setCourses(res.data);
      }
    });
  }, []);

  useEffect(() => {}, [courses, route.params?.request]);

  const showAlert = () => {
    Alert.alert(
      "Veranstaltung: " + courses[0].description,
      "Wähle eine Aktion aus",
      [
        {
          text: "Abbrechen",
          onPress: () => {},
          style: "cancel",
        },
        { text: "Löschen", onPress: () => console.log("Gelöscht") },
        {
          text: "Ändern",
          onPress: () => navigation.navigate("CourseInfoModal"),
        },
      ],
      { cancelable: true }
    );
  };

  return (
    <View style={styles.container}>
      {Platform.OS !== "web" ? (
        <FlatList
          data={courses}
          renderItem={({ item }) => (
            <Card key={courses.length} onLongPress={() => showAlert()}>
              <Text style={styles.courseHeader}>Veranstaltung:</Text>
              <Text style={{ fontSize: 20, marginBottom: 15 }}>
                {item.description}
              </Text>
              <Text style={styles.boldText}>Professor:</Text>
              <Text>{item.professor}</Text>
              <Text style={styles.boldText}>ECTS:</Text>
              <Text>{item.ects}</Text>
              <Text style={styles.boldText}>Empfohlenes Semester:</Text>
              <Text>{item.requiredSemester}</Text>
            </Card>
          )}
          keyExtractor={(item) => item.id}
        />
      ) : (
        <table>
          <tr style={{ fontSize: 16 }}>
            <th>VERANSTALTUNG</th>
            <th>PROFESSOR</th>
            <th>ECTS</th>
            <th>EMPFOHLENES SEMESTER</th>
          </tr>
          {courses.map((course) => {
            return (
              <tr style={{ textAlign: "center" }}>
                <td>{course.description}</td>
                <td>{course.professor}</td>
                <td>{course.ects}</td>
                <td>{course.requiredSemester}</td>
                <td>
                  <FontAwesome5.Button
                    name="edit"
                    color="black"
                    backgroundColor="#ffff"
                  />
                </td>
                <td>
                  <FontAwesome5.Button
                    name="trash-alt"
                    color="black"
                    backgroundColor="#ffff"
                  />
                </td>
              </tr>
            );
          })}
        </table>
      )}
    </View>
  );
};

export default CourseScreen;
