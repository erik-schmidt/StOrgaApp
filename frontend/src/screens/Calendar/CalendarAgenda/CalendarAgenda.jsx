import React, { useState, useEffect } from "./node_modules/react";
import styles from "./CalendarAgenda.style";
import { Text, View } from "react-native";
import { Agenda } from "./node_modules/react-native-calendars";
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
    console.log("Current Appointments", appointments);
  }, []);

  useEffect(() => {}, [appointments]);

  LocalConfig;

  const termin = { key: "termin", color: "red", selectedDotColor: "blue" };

  return (
    <Agenda
      items={{
        appointments,
        "2020-04-22": [
          {
            timeStart: "8:00",
            timeEnd: "11:00",
            name: "appointment1",
            info: "das ist hard gecodet",
          },
        ],
        "2020-04-23": [
          {
            timeStart: "10:00",
            timeEnd: "12:30",
            name: "appointment2",
            info: "das ist hard gecodet",
          },
        ],
        "2020-04-22": [
          {
            timeStart: "9:00",
            timeEnd: "11:00",
            name: "appointment3",
            info: "das ist hard gecodet",
          },
        ],
      }}
      markedDates={{
        "2020-04-22": { dots: [termin], color: "red" },
        "2020-04-23": { dots: [termin], color: "red" },
      }}
      markingType={"multi-dot"}
      loadItemsForMonth={(month) => {
        console.log("month loading");
      }}
      onCalendarToggled={(calendarOpened) => {
        console.log(calendarOpened);
      }}
      onDayPress={(day) => {
        console.log("day pressed"), appointments;
      }}
      onDayChange={(day) => {
        console.log("day changed");
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
