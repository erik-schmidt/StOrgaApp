import React, { useState, useEffect } from "react";
import { View } from "react-native";
import WeeklyCalendar from "react-native-weekly-calendar";
import styles from "./WeeklyCalendar.style";
import moment from "moment";
import "moment/locale/de";
import { useRoute } from "@react-navigation/native";
//import Appointment from "../../../models/appointment";
//import { getAppointments } from "../../../api/services/CalendarService";

const WeekCalendar = () => {
  const route = useRoute();
  const [appointments, setAppointments] = useState([]);
  const [date, setDate] = useState(new Date());

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
    console.table("Termin:", appointments.values);
  }, [route]);

  useEffect(() => {}, [appointments]);

  const sampleEvents = [
    { start: "2020-05-23 09:00:00", duration: "00:20:00", note: "Walk my dog" },
    {
      start: "2020-06-24 14:00:00",
      duration: "01:00:00",
      note: "Doctor's appointment",
    },
    {
      start: "2020-05-25 08:00:00",
      duration: "00:30:00",
      note: "Morning exercise",
    },
    {
      start: "2020-05-25 14:00:00",
      duration: "02:00:00",
      note: "Meeting with client",
    },
    {
      start: "2020-05-25 19:00:00",
      duration: "01:00:00",
      note: "Dinner with family",
    },
    { start: "2020-05-13 09:30:00", duration: "01:00:00", note: "Schedule 1" },
    { start: "2020-05-13 11:00:00", duration: "02:00:00", note: "Schedule 2" },
    { start: "2020-05-13 15:00:00", duration: "01:30:00", note: "Schedule 3" },
    { start: "2020-05-13 18:00:00", duration: "02:00:00", note: "Schedule 4" },
    { start: "2020-05-13 22:00:00", duration: "01:00:00", note: "Schedule 5" },
  ];

  return (
    <View style={styles.container}>
      <WeeklyCalendar
        events={sampleEvents}
        style={{ height: 600 }}
        selected={moment(date)}
        locale="de"
      />
    </View>
  );
};
export default WeekCalendar;
