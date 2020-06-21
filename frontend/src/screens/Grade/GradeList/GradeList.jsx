import React, { useState, useEffect } from "react";
import styles from "./GradeList.style";
import { getGrades, getAverage } from "../../../api/services/GradeService";
import * as HttpStatus from "http-status-codes";
import { useNavigation, useRoute } from "@react-navigation/native";
import { FlatList } from "react-native-gesture-handler";
import { View, Text, RefreshControl } from "react-native";
import Card from "../../../components/Card/Card";
import AuthContext from "../../../constants/AuthContext";
import { useFocusEffect } from "@react-navigation/native";

const GradeList = () => {
  const navigation = useNavigation();
  const route = useRoute();
  const [grades, setGrades] = useState([]);
  const [refreshing, setRefreshing] = useState();
  const [average, setAverage] = useState();
  const { signOut } = React.useContext(AuthContext);

  useFocusEffect(
    React.useCallback(() => {
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
    }, [])
  );

  const onRefresh = () => {
    setRefreshing(false);
    getGrades()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setGrades(res.data);
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
  };

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
  }, [route]);

  return (
    <View style={styles.container}>
      <FlatList
        data={grades}
        refreshControl={
          <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
        }
        ListEmptyComponent={() => {
          return (
            <View style={styles.container}>
              <Text
                style={{
                  fontSize: 20,
                  fontWeight: "bold",
                  textAlign: "center",
                  marginTop: "80%",
                  color: "#66CDAA",
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
              navigation.navigate("GradeMenu", { grade: item })
            }
          >
            <View>
              <Text style={styles.gradeHeader}>
                Kursname: {item.courseName}
              </Text>
              <Text style={styles.cardText}>
                Kursnummer: {item.courseNumber}
              </Text>
            </View>
            <View style={styles.cardText}>
              <Text style={styles.boldText}>Note: </Text>
              <Text>{item.grade}</Text>
            </View>
          </Card>
        )}
        keyExtractor={(item) => item.courseNumber}
      />
      <Text
        style={{
          textAlign: "right",
          marginRight: 10,
          marginBottom: 5,
          fontSize: 18,
          fontWeight: "bold",
        }}
      >
        Durchschnitt: {average}
      </Text>
    </View>
  );
};

export default GradeList;
