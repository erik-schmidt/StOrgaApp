import React, { useState, useEffect } from "react";
import { Text, View, RefreshControl, SectionList } from "react-native";
import {
  getCoursesByRecommendedSemester,
  getAllCourses,
} from "../../../api/services/CourseService";
import Card from "../../../components/Card/Card";
import styles from "./CourseList.style";
import { useNavigation, useRoute } from "@react-navigation/native";
import * as HttpStatus from "http-status-codes";
import AuthContext from "../../../constants/AuthContext";
import AppButton from "../../../components/AppButton/AppButton";
import { useFocusEffect } from "@react-navigation/native";

const MajorStudiesList = () => {
  const [courses, setCourses] = useState([]);
  const navigation = useNavigation();
  const route = useRoute();
  const [refreshing, setRefreshing] = useState(false);
  const { signOut } = React.useContext(AuthContext);

  useFocusEffect(
    React.useCallback(() => {
      getCoursesByRecommendedSemester()
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
    }, [])
  );

  const onRefresh = () => {
    setRefreshing(true);
    getCoursesByRecommendedSemester()
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
    getCoursesByRecommendedSemester()
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
    getCoursesByRecommendedSemester()
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
      <SectionList
        sections={[{ title: "", data: courses }]}
        refreshControl={
          <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
        }
        keyExtractor={(item, index) => item + index}
        renderItem={({ item }) => (
          <View>
            <Text style={styles.sectionHeader}>{item.name}</Text>
            {item.courseList.map((course) => {
              return (
                <Card
                  key={courses.number}
                  onLongPress={() =>
                    navigation.navigate("CourseMenu", {
                      course: course,
                    })
                  }
                  onPress={() =>
                    navigation.navigate("CourseInformationModal", {
                      course: course,
                    })
                  }
                >
                  <View>
                    <Text style={styles.courseHeader}>Veranstaltung: </Text>
                    <Text style={styles.courseDescription}>
                      {course.description}
                    </Text>
                  </View>
                  <View style={styles.cardText}>
                    <Text style={styles.boldText}>ECTS: </Text>
                    <Text>{course.ects}</Text>
                  </View>
                  <View style={styles.cardText}>
                    <Text style={styles.boldText}>Empfohlenes Semester: </Text>
                    <Text>{course.recommendedSemester}</Text>
                  </View>
                </Card>
              );
            })}
          </View>
        )}
        keyExtractor={(item) => item.description}
      />
    </View>
  );
};

export default MajorStudiesList;
