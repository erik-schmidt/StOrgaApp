import React, { useState, useEffect } from "react";
import { Text, View, RefreshControl, Modal, AsyncStorage } from "react-native";
import { getAllCourses } from "../../../api/services/CourseService";
import { FlatList } from "react-native-gesture-handler";
import Card from "../../../components/Card/Card";
import styles from "./CourseList.style";
import { useNavigation, useRoute } from "@react-navigation/native";
import Toast from "../../../components/Toast/Toast";
import Axios from "axios";

const CourseList = (props) => {
  const [courses, setCourses] = useState(props.courses);
  const navigation = useNavigation();
  const route = useRoute();
  const [refreshing, setRefreshing] = useState(false);
  const [showModal, setShowModal] = useState(false);

  const onRefresh = () => {
    setRefreshing(true);
    getAllCourses()
      .then((res) => {
        if (res != undefined) {
          setCourses(res.data);
          setRefreshing(false);
        } else {
          throw new Error();
        }
      })
      .catch((err) => {
        setShowModal(true);
        setRefreshing(false);
        setTimeout(() => {
          setShowModal(false);
        }, 5000);
      });
  };

  useEffect(() => {
    getAllCourses()
      .then((res) => {
        if (res != undefined) {
          setCourses(res.data);
        } else {
          throw new Error();
        }
      })
      .catch((err) => {
        setShowModal(true);
        setTimeout(() => {
          setShowModal(false);
        }, 5000);
      });
  }, []);

  useEffect(() => {
    getAllCourses()
      .then((res) => {
        if (res != undefined) {
          setCourses(res.data);
        } else {
          throw new Error();
        }
      })
      .catch((err) => {
        setShowModal(true);
        setTimeout(() => {
          setShowModal(false);
        }, 5000);
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
              <Text>{item.reccomendedSemester}</Text>
            </View>
          </Card>
        )}
        keyExtractor={(item) => item.description}
      />
      <Toast
        showModal={showModal}
        color="red"
        text="Keine Verbindung zum Server"
      />
    </View>
  );
};

export default CourseList;
