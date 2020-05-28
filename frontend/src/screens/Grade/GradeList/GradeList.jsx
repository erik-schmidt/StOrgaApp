import React, { useState, useEffect } from "react";
import styles from "./GradeList.style";
import { getGrades, getAverage } from "../../../api/services/GradeService";
import * as HttpStatus from "http-status-codes";
import { useNavigation, useRoute } from "@react-navigation/native";
import { FlatList } from "react-native-gesture-handler";
import { View, Text } from 'react-native';
import Card from "../../../components/Card/Card";

const GradeList = () => {
  const navigation = useNavigation();
  const route = useRoute();
  const [grades, setGrades] = useState([]);
  const [average, setAverage] = useState();

  useEffect(() => {
    getGrades()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setGrades(res.data);
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
    getAverage()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setAverage(res.data);
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
  }, []);

  useEffect(() => {
    getGrades()
      .then((res) => {
        if (res.status === HttpStatus.Ok) {
          setGrades(res.data);
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
    getAverage()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setAverage(res.data);
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
        data={grades}
        renderItem={({ item }) => (
          <Card
            onLongPress={() => navigation.navigate("CourseMenu", {grade: item})}
            onPress={() => console.log("pressed")}
          >
            <View>
              <Text style={styles.gradeHeader}>Kursnummer: </Text>
              <Text style={styles.gradeDescription}>{item.courseNumber}</Text>
            </View>
            <View style={styles.cardText}>
              <Text style={styles.boldText}>Note: </Text>
              <Text>{item.grade}</Text>
            </View>
          </Card>
        )}
        keyExtractor={(item) => item.courseNumber}
      />
    </View>
  );
};

export default GradeList;
