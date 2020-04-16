import React, { useState, useEffect } from "react";
import styles from "./CourseScreen.style";
import { Text, View, Platform } from "react-native";
import { FlatList } from "react-native-gesture-handler";
import Card from "../../components/CardList/Card";
import SeperatedText from "../../components/SeperatedText/SeperatedText";
import { getAllCourses } from "../../api/services/courseService";

const CourseScreen = ({ route }) => {
  const [courses, setCourses] = useState([]);

  useEffect(() => {
    getAllCourses().then(res => {
      setCourses(res.data);
      console.log(res);
    })
  }, []);

  useEffect(() => {
    console.log("Current Courses", courses);
  }, [courses, route.params?.request]);

  return (
    <View style={styles.container}>
      <FlatList
        data={courses}
        renderItem={({ item }) => (
          <Card>
            <SeperatedText title="Name:" content={item.description} />
            <SeperatedText title="Raum:" content={item.room} />
            <SeperatedText title="Professor:" content={item.professor} />
            <SeperatedText title="ECTS:" content={item.ects} />
          </Card>
        )}
        keyExtractor={(item) => item.id}
      />
    </View>
  );
};

export default CourseScreen;
