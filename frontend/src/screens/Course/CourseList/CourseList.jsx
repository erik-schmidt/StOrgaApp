import React, { useState, useEffect } from "react";
import { Text, View, RefreshControl, Alert } from "react-native";
import { getAllCourses, getAllStudentCourses } from "../../../api/services/CourseService";
import { FlatList } from "react-native-gesture-handler";
import Card from "../../../components/Card/Card";
import styles from "./CourseList.style";
import { useNavigation, useRoute } from "@react-navigation/native";
import Toast from "../../../components/Toast/Toast";
import * as HttpStatus from "http-status-codes";

const CourseList = () => {
  const [courses, setCourses] = useState([]);
  const navigation = useNavigation();
  const route = useRoute();
  const [refreshing, setRefreshing] = useState(false);

  const onRefresh = () => {
    setRefreshing(true);
    getAllStudentCourses().then(res => {
      if (res.status === HttpStatus.OK) {
        setCourses(res.data);
        setRefreshing(false);
      } else {
        setRefreshing(false);
        throw new Error(res.data);
      }
    }).catch(err => {
      alert(err);
    })
    setTimeout(() => {
      setRefreshing(false);
    }, 2000)
  };

  useEffect(() => {
    getAllStudentCourses().then(res => {
      if (res.status === HttpStatus.OK) {
        setCourses(res.data);
      } else {
        throw new Error(res.data)
      }
    }).catch(err => {
      alert(err);
    })
  }, []);

  useEffect(() => {
    getAllStudentCourses().then(res => {
      if (res.status === HttpStatus.OK) {
        setCourses(res.data);
      } else {
        throw new Error(res.data)
      }
    }).catch(err => {
      alert(err);
    })
  }, [route]);

  return (
    <View style={styles.container}>
      <FlatList
        data={courses}
        refreshControl={
          <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
        }
        renderItem={({ item }) => (
          <Card
            key={courses.length}
            onLongPress={() =>
              navigation.navigate("CourseMenu", { course: item })
            }
            onPress={() =>
              navigation.navigate("CourseInformationModal", { course: item })
            }
          >
            <View>
              <Text style={styles.courseHeader}>Veranstaltung: </Text>
              <Text style={styles.courseDescription}>{item.description}</Text>
            </View>
            <View style={styles.cardText}>
              <Text style={styles.boldText}>ECTS: </Text>
              <Text>{item.ects}</Text>
            </View>
            <View style={styles.cardText}>
              <Text style={styles.boldText}>Empfohlenes Semester: </Text>
              <Text>{item.reccomendedSemester}</Text>
            </View>
          </Card>
        )}
        keyExtractor={(item) => item.description}
      />
    </View>
  );
};

export default CourseList;
