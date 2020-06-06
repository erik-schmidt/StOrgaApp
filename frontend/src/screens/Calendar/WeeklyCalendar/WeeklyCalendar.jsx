import React, { useState, useEffect } from "react";
import { RefreshControl, View, Text, Dimensions } from "react-native";
import WeeklyCalendar from "react-native-weekly-calendar";
import styles from "./WeeklyCalendar.style";
import moment from "moment";
import "moment/locale/de";
import { TouchableHighlight } from "react-native-gesture-handler";
import { useNavigation, useRoute } from "@react-navigation/native";
import {
  getAppointments,
  pingCalendar,
  getAllAppointments,
} from "../../../api/services/CalendarService";
import * as HttpStatus from "http-status-codes";

//TO DO: Alert für on Press und Long Press einfügen

const WeekCalendar = () => {
  const navigation = useNavigation();
  const route = useRoute();
  const [appointments, setAppointments] = useState([]);
  const [selectedDay, setSelectedDay] = useState(new Date());
  const [refreshing, setRefreshing] = useState(false);

  const onRefresh = () => {
    setRefreshing(true);
    getAllAppointments()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setAppointments(res.data);
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
    pingCalendar();
    getAllAppointments()
      .then((res) => {
        console.log(res);
        if (res.status === HttpStatus.OK) {
          setAppointments(res.data);
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

  useEffect(() => {}, [appointments]);

  return (
    /*<View
      refreshControl={
        <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
      }
    >*/
    <WeeklyCalendar
      events={appointments}
      style={{ height: Dimensions.get("window").height }}
      locale="de"
      selectedDay="weekday"
      renderEvent={(event, j) => {
        const startTime = moment(event.entryStartTime).format("LTS").toString;
        const endTime = moment(event.entryFinishTime).format("LTS").toString;

        return (
          <TouchableHighlight
            onPress={() =>
              navigation.navigate("CalendarInformationModal", {
                appointment: event,
              })
            }
            onLongPress={() =>
              navigation.navigate("CalendarMenu", { appointment: event })
            }
          >
            <View key={j}>
              <View style={styles.event}>
                <View style={styles.eventDuration}>
                  <View style={styles.durationContainer}>
                    <View style={styles.durationDot} />
                    <Text style={styles.durationText}>{startTime}</Text>
                  </View>
                  <View style={{ paddingTop: 10 }} />
                  <View style={styles.durationContainer}>
                    <View style={styles.durationDot} />
                    <Text style={styles.durationText}>{endTime}</Text>
                  </View>
                  <View style={styles.durationDotConnector} />
                </View>
                <View style={styles.eventNote}>
                  <Text style={styles.eventText}>{event.name}</Text>
                </View>
              </View>
              <View style={styles.lineSeparator} />
            </View>
          </TouchableHighlight>
        );
      }}
      renderLastEvent={(event, j) => {
        const startTime = moment(event.entryStartTime).format("LT").toString;
        const endTime = moment(event.entryFinishTime).format("LT").toString;

        return (
          <TouchableHighlight
            onPress={() =>
              navigation.navigate("CalendarInformationModal", {
                appointment: event,
              })
            }
            onLongPress={() =>
              navigation.navigate("CalendarMenu", { appointment: event })
            }
          >
            <View key={j}>
              <View style={styles.event}>
                <View style={styles.eventDuration}>
                  <View style={styles.durationContainer}>
                    <View style={styles.durationDot} />
                    <Text style={styles.durationText}>{startTime}</Text>
                  </View>
                  <View style={{ paddingTop: 10 }} />
                  <View style={styles.durationContainer}>
                    <View style={styles.durationDot} />
                    <Text style={styles.durationText}>{endTime}</Text>
                  </View>
                  <View style={styles.durationDotConnector} />
                </View>
                <View style={styles.eventNote}>
                  <Text style={styles.eventText}>{event.name}</Text>
                </View>
              </View>
            </View>
          </TouchableHighlight>
        );
      }}
      renderDay={(eventViews, weekdayToAdd, i) => (
        <View key={i.toString()} style={styles.day}>
          <View style={styles.dayLabel}>
            <Text style={[styles.monthDateText, { color: "#66CDAA" }]}>
              {weekdayToAdd.format("M/D").toString()}
            </Text>
            <Text style={[styles.dayText, { color: "grey" }]}>
              {weekdayToAdd.format("ddd").toString()}
            </Text>
          </View>
          <View
            style={[
              styles.allEvents,
              eventViews.length === 0
                ? { width: "100%", backgroundColor: "lightgrey" }
                : {},
            ]}
          >
            {eventViews}
          </View>
        </View>
      )}
      onDayPress={(weekday) => {
        <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />;

        setSelectedDay(moment(weekday).format("L"));
      }}
    />
    //</View>
  );
};
export default WeekCalendar;
