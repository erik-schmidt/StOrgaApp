import React, { useState, useEffect } from "react";
import styles from "./GradeList.style";
import { getGrades, getAverage } from "../../../api/services/GradeService";
import * as HttpStatus from "http-status-codes";
import { useNavigation, useRoute } from "@react-navigation/native";
import { FlatList } from "react-native-gesture-handler";
import { View, Text } from "react-native";
import Card from "../../../components/Card/Card";
import AuthContext from "../../../constants/AuthContext";

const GradeList = () => {
  const navigation = useNavigation();
  const route = useRoute();
  const [grades, setGrades] = useState([]);
  const [average, setAverage] = useState();
  const { signOut } = React.useContext(AuthContext);

  useEffect(() => {
    getGrades()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setGrades(res.data);
        } else if (res.status === HttpStatus.UNAUTHORIZED) {
          signOut();
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
    getGrades()
      .then((res) => {
        if (res.status === HttpStatus.Ok) {
          setGrades(res.data);
        } else if (res.status === HttpStatus.UNAUTHORIZED) {
          signOut();
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
        data={grades}
        ListEmptyComponent={() => {
          return (
            <View style={styles.container}>
              <Text
                style={{
                  fontSize: 20,
                  fontWeight: "bold",
                  textAlign: "center",
                }}
              >
                Keine Noten gespeichert
              </Text>
            </View>
          );
        }}
        renderItem={({ item }) => (
          <Card
            onLongPress={() =>
              navigation.navigate("CourseMenu", { grade: item })
            }
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
      <Text style={{ textAlign: "right" }}>Durchschnitt: {average}</Text>
    </View>
  );
};

export default GradeList;
