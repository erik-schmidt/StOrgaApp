import React, { useState, useEffect } from "react";
import { Text, View, RefreshControl, Alert } from "react-native";
import { getAllStudentCourses } from "../../../api/services/CourseService";
import { FlatList } from "react-native-gesture-handler";
import Card from "../../../components/Card/Card";
import styles from "./CourseList.style";
import { useNavigation, useRoute } from "@react-navigation/native";
import * as HttpStatus from "http-status-codes";
import AuthContext from "../../../constants/AuthContext";
import { useFocusEffect } from "@react-navigation/native";
import AppButton from "../../../components/AppButton/AppButton";

const CourseList = () => {
  const [courses, setCourses] = useState([]);
  const navigation = useNavigation();
  const route = useRoute();
  const [refreshing, setRefreshing] = useState(false);
  const { signOut } = React.useContext(AuthContext);

  useFocusEffect(
    React.useCallback(() => {
      getAllStudentCourses()
        .then((res) => {
          if (res.status === HttpStatus.OK) {
            setCourses(res.data);
            setRefreshing(false);
          } else if (res.status === HttpStatus.UNAUTHORIZED) {
            signOut();
          } else {
            throw new Error(res.data);
          }
        })
        .catch((err) => {
          alert(err);
        });
    }, [])
  );

  const onRefresh = () => {
    setRefreshing(true);
    getAllStudentCourses()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setCourses(res.data);
          setRefreshing(false);
        } else if (res.status === HttpStatus.UNAUTHORIZED) {
          signOut();
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
  };

  useEffect(() => {
    getAllStudentCourses()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setCourses(res.data);
        } else if (res.status === HttpStatus.UNAUTHORIZED) {
          signOut();
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
  }, []);

  useEffect(() => {
    getAllStudentCourses()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setCourses(res.data);
        } else if (res.status === HttpStatus.UNAUTHORIZED) {
          signOut();
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
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
              <Text>{item.recommendedSemester}</Text>
            </View>
          </Card>
        )}
        keyExtractor={(item) => item.description}
      />
    </View>
  );
};

export default CourseList;
