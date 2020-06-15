import React, { useState } from "react";
import styles from "./TimetableInformationModal.style";
import { View, Text } from "react-native";
import { AntDesign } from "@expo/vector-icons";
import moment from "moment";

const TimetableInformationModal = ({ navigation, route }) => {
  const [courses, setCouses] = useState(route.params?.courses);

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Kurs: {courses.summary}</Text>
      <Text style={styles.text}>
        Datum : {moment(courses.date).format("LL")}
      </Text>
      <Text style={styles.text}>Start : {courses.startTime} Uhr</Text>
      <Text style={styles.text}>Ende : {courses.finishTime} Uhr</Text>
      <Text style={styles.text}>Nummer: {courses.courseNumber}</Text>
      <Text style={styles.text}>Raum: {courses.location}</Text>
      <Text style={styles.text}>Semester: {courses.fieldOfStudySemester}</Text>
      <Text style={styles.text}>Infos: {courses.description}</Text>
    </View>
  );
};

export default TimetableInformationModal;
