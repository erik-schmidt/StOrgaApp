import React, { useState, useEffect } from "react";
import { Text, View } from "react-native";
import { getAllCourses } from "../../../api/services/courseService";
import { FlatList } from "react-native-gesture-handler";
import Card from "../../../components/Card/Card";
import styles from "./CourseList.style";
import { useNavigation } from "@react-navigation/native";

const CourseList = (props) => {
  const [courses, setCourses] = useState([]);
  const navigation = useNavigation();

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
            onLongPress={() =>
              navigation.navigate("CourseMenu", { course: item })
            }
            onPress={props.onPress}
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
    </View>
  );
};

export default CourseList;
