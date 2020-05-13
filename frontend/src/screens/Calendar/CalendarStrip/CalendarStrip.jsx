import React, { useState, useEffect } from "react";
import { View, TouchableOpacity } from "react-native";
import CalendarStrip from "react-native-calendar-strip";
import styles from "./CalendarStrip.style";
import moment from "moment";
import "moment/locale/de";
import { useRoute } from "@react-navigation/native";
import Appointment from "../../../models/appointment";
import { TextInput } from "react-native-gesture-handler";
//import { getAppointments } from "../../../api/services/CalendarService";

const CalStrip = () => {
  const route = useRoute();
  const [appointments, setAppointments] = useState([]);
  const [date, setDate] = useState("");

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

  return (
    <View style={styles.container}>
      <CalendarStrip
        style={{ height: 150, paddingTop: 20, paddingBottom: 10 }}
        daySelectionAnimation={{
          type: "border",
          duration: 200,
          borderWidth: 1,
          borderHighlightColor: "black",
        }}
        calendarColor={"#E0FFFF"}
        onDateSelected={
          ((date) => setAppointments(date),
          console.log("onDateSelected: ", appointments))
        }
        selectedDate={moment.date}
      />
      <TextInput style={styles.description}>
        appointments: {appointments}
      </TextInput>
    </View>
  );
};
moment.locale("de");
moment().format("D MMM YY");

export default CalStrip;
