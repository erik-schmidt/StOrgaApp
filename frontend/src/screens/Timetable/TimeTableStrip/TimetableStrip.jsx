import React, { useState, useEffect } from "react";
import CalendarStrip from "react-native-calendar-strip";
import styles from "./TimetableStrip.style";
import { Text, RefreshControl, View, FlatList, Dimensions } from "react-native";
import { useNavigation } from "@react-navigation/native";
import * as HttpStatus from "http-status-codes";
import Card from "../../../components/Card/Card";
import moment from "moment";
import "moment/locale/de";
import { getAllCourses } from "../../../api/services/TimetableService";

const TimetableStrip = () => {
  const navigation = useNavigation();
  const [courses, setCourses] = useState([]);

  useEffect(() => {
    getAllCourses()
      .then((res) => {
        console.log(res);
        if (res.status === HttpStatus.OK) {
          setCourses(res.data);
          console.log(res.data);
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

  useEffect(() => {}, [courses]);
  moment.locale("de");

  return (
    <View style={styles.container}>
      <CalendarStrip
        style={styles.stripContainer}
        daySelectionAnimation={{
          type: "border",
          duration: 200,
          borderWidth: 1,
          borderHighlightColor: "#66CDAA",
        }}
        calendarColor={"white"}
        calendarHeaderStyle={{ color: "#66CDAA" }}
        highlightDateNumberStyle={{ color: "#66CDAA" }}
      />
      <View style={styles.container}>
        <FlatList
          syle={{ hight: Dimensions.get("window").height }}
          data={courses}
          /*refreshControl={
            <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
          }*/
          renderItem={({ item }) => (
            <Card
              key={courses.length}
              onPress={() =>
                navigation.navigate("TimetableInformationModal", {
                  courses: item,
                })
              }
            >
              <View style={styles.event}>
                <View style={styles.eventDuration}>
                  <View style={styles.durationContainer}>
                    <Text style={styles.dateText}>
                      {moment(item.date).format("l")}
                    </Text>
                  </View>
                </View>
                <View style={styles.eventDuration}>
                  <View style={styles.durationContainer}>
                    <View style={styles.durationDot} />
                    <Text style={styles.durationText}>{item.startTime}</Text>
                  </View>
                  <View style={{ paddingTop: 10 }} />
                  <View style={styles.durationContainer}>
                    <View style={styles.durationDot} />
                    <Text style={styles.durationText}>{item.finishTime}</Text>
                  </View>
                  <View style={styles.durationDotConnector} />
                </View>
                <View style={styles.eventNote}>
                  <Text style={styles.eventText}>{item.description}</Text>
                </View>
              </View>
            </Card>
          )}
          keyExtractor={(item) => item.name}
        />
      </View>
    </View>
  );
};
export default TimetableStrip;
