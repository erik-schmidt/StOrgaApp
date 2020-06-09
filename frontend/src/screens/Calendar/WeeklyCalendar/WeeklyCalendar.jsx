import React, { useState, useEffect, useCallback } from "react";
import {
  RefreshControl,
  ScrollView,
  View,
  Text,
  Dimensions,
} from "react-native";
import WeeklyCalendar from "react-native-weekly-calendar";
import styles from "./WeeklyCalendar.style";
import moment from "moment";
import "moment/locale/de";
import { TouchableHighlight } from "react-native-gesture-handler";
import { useNavigation, useRoute } from "@react-navigation/native";
//import { getAppointments } from "../../../api/services/CalendarService";

//TO DO: Toast für on Press und Long Press einfügen

const WeekCalendar = () => {
  const navigation = useNavigation();
  const route = useRoute();
  const [appointments, setAppointments] = useState([]);
  const [selectedDay, setSelectedDay] = useState(new Date());
  const [refreshing, setRefreshing] = useState(false);

  /*const onRefresh = useCallback(() => {
    setRefreshing(true);
    wait(2000).then(() => setRefreshing(false));
  }, [refreshing]);*/

  const onRefresh = () => {
    setRefreshing(true);
  };

  /*useEffect(() => {
    getAppointments().then((res) => {
      if (res !== undefined) {
        setAppointments(res.data);
      }
    });
  }, []);*/

  useEffect(() => {
    console.log("selectedDay: ", selectedDay);
    if (route.params?.entry !== undefined) {
      setAppointments([...appointments, route.params?.entry]);
    }
    console.log("Neuer Eintrag: -----------------------------xxxxxxx");
    console.log("Termin:", appointments);
  }, [route]);

  useEffect(() => {}, [appointments]);

  const sampleEvents =
    /*[
    appointments.name,
    appointments.end,
    appointments.start,
    appointments.info,
  ];*/
    [
      {
        start: "2020-05-23 09:00:00",
        end: "2020-05-23 10:20:00",
        name: "Walk my dog",
        info: "infotext",
      },

      {
        start: "2020-05-13 09:30:00",
        end: "2020-05-13 01:00:00",
        name: "Schedule 1",
        info: "infotext",
      },
      {
        start: "2020-05-13 11:00:00",
        end: "2020-05-13 14:00:00",
        name: "Schedule 2",
        info: "infotext",
      },
      {
        start: "2020-05-13 15:00:00",
        end: "2020-05-13 15:30:00",
        name: "Schedule 3",
        info: "infotext",
      },
      {
        start: "2020-05-13 18:00:00",
        end: "2020-05-13 19:00:00",
        name: "Schedule 4",
        info: "infotext",
      },
      {
        start: "2020-05-13 22:00:00",
        end: "2020-05-13 23:00:00",
        name: "Schedule 5",
        info: "infotext",
      },
    ];

  return (
    <ScrollView
      refreshControl={
        ((<RefreshControl refreshing={refreshing} onRefresh={onRefresh} />),
        (<Text>Pull down to refresh </Text>))
      }
    >
      <WeeklyCalendar
        events={sampleEvents}
        style={{ height: Dimensions.get("window").height }}
        locale="de"
        selectedDay="weekday"
        renderEvent={(event, j) => {
          let startTime = moment(event.start).format("LT").toString();
          let endTime = moment(event.end).format("LT").toString();

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
          let startTime = moment(event.start).format("LT").toString();
          let endTime = moment(event.end).format("LT").toString();

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
        onDayPress={(weekday, i) => {
          setSelectedDay(moment(weekday).format("L"));
          /*console.log(
            weekday.format("L") +
              " is selected! And it is day " +
              (i + 1) +
              " of the week!"
          );*/
        }}
      />
    </ScrollView>
  );
};
export default WeekCalendar;
