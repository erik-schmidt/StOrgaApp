import React, { useState } from "react";
import CalendarStrip from "react-native-calendar-strip";
import styles from "./CalendarStrip.style";
import { Text, View, FlatList, Dimensions } from "react-native";
import { useNavigation } from "@react-navigation/native";
import { getWeeklyAppointments } from "../../../api/services/CalendarService";
import * as HttpStatus from "http-status-codes";
import Card from "../../../components/Card/Card";
import moment from "moment";
import "moment/locale/de";

const CalStrip = () => {
  const navigation = useNavigation();
  const [weeklyAppointments, setWeeklyAppointments] = useState([]);

  moment.locale("de");

  const getWeekApps = (start) => {
    const startDate = moment(start).format("YYYY-MM-DD");
    const endDate = moment(start).add(6, "days").format("YYYY-MM-DD");

    getWeeklyAppointments({ startDate: startDate, endDate: endDate })
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setWeeklyAppointments(res.data);
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
        onWeekChanged={(start) => getWeekApps(start)}
      />
      <View style={styles.container}>
        <FlatList
          syle={{ hight: Dimensions.get("window").height }}
          data={weeklyAppointments}
          ListEmptyComponent={
            <Text style={styles.emptyList}>Keine Termine in dieser Woche</Text>
          }
          renderItem={({ item }) => (
            <Card
              key={weeklyAppointments.length}
              onPress={() =>
                navigation.navigate("CalendarInformationModal", {
                  appointment: item,
                })
              }
              onLongPress={() =>
                navigation.navigate("CalendarMenu", { appointment: item })
              }
            >
              <View style={styles.event}>
                <View style={styles.eventDuration}>
                  <View style={styles.durationContainer}>
                    <Text style={styles.dateText}>
                      {moment(item.entryDate).format("l")}
                    </Text>
                  </View>
                </View>
                <View style={styles.eventDuration}>
                  <View style={styles.durationContainer}>
                    <View style={styles.durationDot} />
                    <Text style={styles.durationText}>
                      {item.entryStartTime}
                    </Text>
                  </View>
                  <View style={{ paddingTop: 10 }} />
                  <View style={styles.durationContainer}>
                    <View style={styles.durationDot} />
                    <Text style={styles.durationText}>
                      {item.entryFinishTime}
                    </Text>
                  </View>
                  <View style={styles.durationDotConnector} />
                </View>
                <View style={styles.eventNote}>
                  <Text style={styles.eventText}>{item.name}</Text>
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

export default CalStrip;
