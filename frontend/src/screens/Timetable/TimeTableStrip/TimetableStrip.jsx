import React, { useState } from "react";
import CalendarStrip from "react-native-calendar-strip";
import styles from "./TimetableStrip.style";
import { Text, View, FlatList, Dimensions } from "react-native";
import { useNavigation } from "@react-navigation/native";
import * as HttpStatus from "http-status-codes";
import Card from "../../../components/Card/Card";
import moment from "moment";
import "moment/locale/de";
import { getCoursesByStartDate } from "../../../api/services/TimetableService";
import { AsyncStorage } from "react-native";

const TimetableStrip = () => {
  const navigation = useNavigation();
  const [weeklyCourses, setWeeklyCourses] = useState([]);

  moment.locale("de");

  const getWeeklyCourses = async (start) => {
    const startDate = moment(start).format("YYYY-MM-DD");
    const endDate = moment(start).add(6, "days").format("YYYY-MM-DD");
    const matrNr = await AsyncStorage.getItem("matrNr");

    getCoursesByStartDate({
      startDate: startDate,
      endDate: endDate,
      matrNr: matrNr,
      currentWeek: false,
      timePeriod: null,
      onlyJoinedCourses: false,
    })
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setWeeklyCourses(res.data);
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
        onWeekChanged={(start) => getWeeklyCourses(start)}
      />
      <View style={styles.container}>
        <FlatList
          syle={{ hight: Dimensions.get("window").height }}
          data={weeklyCourses}
          ListEmptyComponent={
            <Text style={styles.emptyList}>Keine Kurse in dieser Woche</Text>
          }
          renderItem={({ item }) => (
            <Card
              key={weeklyCourses.length}
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
                  <Text style={styles.eventText}>{item.summary}</Text>
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
