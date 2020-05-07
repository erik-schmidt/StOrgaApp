import React, { useState, useEffect } from "react";
import styles from "./CalendarAgenda.style";
import { Text, View } from "react-native";
import { Agenda } from "react-native-calendars";
//import { getAppointments } from "../../../api/services/CalendarService";
import LocalConfig from "./LocalConfig";
import { useRoute } from "@react-navigation/native";
import Appointment from "../../../models/appointment";

const CalendarAgenda = () => {
  const route = useRoute();
  const [appointments, setAppointments] = useState([]);

  /*useEffect(() => {
    getAppointments().then((res) => {
      if (res !== undefined) {
        setAppointments(res.data);
      }
    });
  }, []);*/

  useEffect(() => {
    console.log("useEffect triggered");
    const entry = route.params?.entry;
    if (entry !== undefined) {
      setAppointments([...appointments, entry]);
    }

    console.log("Neuer Eintrag: -----------------------------");
    console.log("Termin:", appointments);
  }, [route]);

  useEffect(() => {}, [appointments]);

  LocalConfig;
  const termin = { key: "termin", color: "red", selectedDotColor: "blue" };

  return (
    <Agenda
      items={{
        "2020-05-06": [
          {
            timeStart: "8:00",
            timeEnd: "09:30",
            name: "MLM",
            info: "Vorlesung - BBB",
          },
        ],
        "2020-05-07": [
          {
            timeStart: "9:45",
            timeEnd: "11:15",
            name: "SMedia",
            info: "Vorlesung - Webex",
          },
        ],
        "2020-05-08": [
          {
            timeStart: "08:00",
            timeEnd: "11:15",
            name: "LabSWP",
            info: "Präsentationen - Webex ",
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
