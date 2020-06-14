import React, { useState } from "react";
import styles from "./TimetableInformationModal.style";
import { View, Text } from "react-native";
import { AntDesign } from "@expo/vector-icons";
import moment from "moment";

const TimetableInformationModal = ({ navigation, route }) => {
  const [courses, setCouses] = useState(route.params?.courses);
  const startTime = moment(courses.startTime).format("LT");
  const endTime = moment(courses.endTime).format("LT");
  const date = moment(courses.date).format("LL");

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Kurs: {courses.description}</Text>
      <Text style={styles.text}>Datum : {date} </Text>
      <Text style={styles.text}>Start : {startTime} Uhr</Text>
      <Text style={styles.text}>Ende : {endTime} Uhr</Text>
      <Text style={styles.text}>Nummer: {courses.courseNumber}</Text>
      <Text style={styles.text}>Raum: {courses.location}</Text>
    </View>
  );
};

export default TimetableInformationModal;
