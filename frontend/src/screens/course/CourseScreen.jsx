import React, { useState, useEffect } from "react";
import styles from "./CourseScreen.style";
import { Text, View, Platform } from "react-native";
import { FlatList } from "react-native-gesture-handler";
import Card from "../../components/CardList/Card";
import { getAllCourses } from "../../api/services/courseService";
import { FontAwesome } from "@expo/vector-icons";

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

  return (
    <View style={styles.container}>
      {Platform.OS !== "web" ? (
        <FlatList
          data={courses}
          renderItem={({ item }) => (
            <Card
              key={courses.length}
              onPress={() => navigation.navigate("CourseInfoModal")}
            >
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
              <FontAwesome.Button
                name="ellipsis-h"
                style={{ justifyContent: "center" }}
                color="grey"
                backgroundColor="#ffff"
              />
            </Card>
          )}
          keyExtractor={(item) => item.id}
        />
      ) : (
        <table>
          <tr>
            <th>Veranstaltung</th>
            <th>Professor</th>
            <th>ECTS</th>
            <th>Empfohlenes Semester</th>
          </tr>
          {courses.map((course) => {
            return (
              <tr style={{ textAlign: "center" }}>
                <td>{course.description}</td>
                <td>{course.professor}</td>
                <td>{course.ects}</td>
                <td>{course.requiredSemester}</td>
              </tr>
            );
          })}
        </table>
      )}
    </View>
  );
};

export default CourseScreen;
