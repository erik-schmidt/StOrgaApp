import React, { useState, useEffect } from "react";
import styles from "./CalendarAgenda.style";
import { Text, View } from "react-native";
import { Agenda } from "react-native-calendars";
import {
  getAppointments,
  pingCalendar,
} from "../../../api/services/CalendarService";
import LocalConfig from "./LocalConfig";

const CalendarAgenda = () => {
  const [appointments, setAppointments] = useState([]);

  useEffect(() => {
    getAppointments().then((res) => {
      if (res !== undefined) {
        setAppointments(res.data);
      }
    });
  }, []);

  useEffect(() => {}, [appointments]);

  LocalConfig;

  const termin = { key: "termin", color: "red", selectedDotColor: "blue" };

  return (
    <Agenda
      items={{
        appointments,
        "2020-05-08": [
          {
            timeStart: "8:00",
            timeEnd: "11:15",
            name: "Lab-SWP",
            info: "Präsentationen",
          },
        ],
        "2020-05-07": [
          {
            timeStart: "09:45",
            timeEnd: "11:15",
            name: "SMedia",
            info: "Vorlesung - Webex",
          },
        ],
        "2020-05-06": [
          {
            timeStart: "8:00",
            timeEnd: "9:30",
            name: "MLM",
            info: "Vorlesung - BBB",
          },
        ],
      }}
      markedDates={{
        "2020-05-08": { dots: [termin], color: "red" },
        "2020-05-07": { dots: [termin], color: "red" },
        "2020-05-06": { dots: [termin], color: "red" },
      }}
      markingType={"multi-dot"}
      loadItemsForMonth={(month) => {
        //console.log("month loading");
      }}
      onCalendarToggled={(calendarOpened) => {
        //console.log(calendarOpened);
      }}
      onDayPress={(day) => {
        //console.log("day pressed"), appointments;
      }}
      onDayChange={(day) => {
        //console.log("day changed");
      }}
      minDate={"2020-01-01"}
      maxDate={"2020-12-31"}
      pastScrollRange={4}
      futureScrollRange={8}
      renderItem={(item) => {
        return (
          <View style={[styles.item, { height: item.height }]}>
            <Text style={styles.time}>
              {item.timeStart + " - " + item.timeEnd}
            </Text>
            <Text style={styles.name}>{item.name}</Text>
            <Text style={styles.info}>{item.info}</Text>
          </View>
        );
      }}
      renderEmptyData={() => {
        return (
          <View style={styles.emptyDate}>
            <Text>No Appointments saved </Text>
          </View>
        );
      }}
    />
  );
};
export default CalendarAgenda;
