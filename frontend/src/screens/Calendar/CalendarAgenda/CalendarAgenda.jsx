import React, { useState, useEffect } from "react";
import styles from "./CalendarAgenda.style";
import { Text, View } from "react-native";
import { Agenda } from "react-native-calendars";
import {
  getAppointments,
  pingCalendar,
} from "../../../api/services/CalendarService";
import LocalConfig from "./LocalConfig";
import * as HttpStatus from "http-status-codes";
import AuthContext from "../../../constants/AuthContext.jsx";

const CalendarAgenda = () => {
  const [appointments, setAppointments] = useState([]);
  const { signOut } = React.useContext(AuthContext);

  useEffect(() => {
    getAppointments()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setAppointments(res.data);
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
      }}
      markedDates={{
        "2020-04-22": { dots: [termin], color: "red" },
        "2020-04-23": { dots: [termin], color: "red" },
      }}
      markingType={"multi-dot"}
      loadItemsForMonth={(month) => {}}
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
