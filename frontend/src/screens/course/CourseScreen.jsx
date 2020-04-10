import React, { useState, useEffect, useLayoutEffect } from "react";
import styles from "./CourseScreen.style";
import { View, Text, Button, Modal } from "react-native";
import CourseScreenNavigator from "../../navigation/CourseScreenNavigator";
import AddButton from "../../components/AddButton";

const CourseScreen = ({ navigation }) => {
  navigation.setParams({ onPress: onClick });
  const [courses, setCourses] = useState([]);
  const [course, setCourse] = useState("");

  const onClick = () => {
    return (
      <Modal
        visible={true}
        onTouchOutside={() => {
          visible: false;
        }}
      />
    );
  };

  useLayoutEffect(() => {
    navigation.setOptions({
      headerRight: () => <AddButton onPress={() => onClick} />,
    });
  })

  useEffect(() => {
    console.log("Current Courses", courses);
  }, [courses]);

  return (
    <View style={styles.container}>
      <View style={styles.container}>
        <table>
          <tr>
            <th>Kurs</th>
          </tr>
          {courses.map((course) => {
            return (
              <tr>
                <td>{course}</td>
              </tr>
            );
          })}
        </table>
      </View>
    </View>
  );
};

export default CourseScreen;
