import React, { useState, useEffect } from "react";
import { View, TouchableOpacity, Text, ScrollView } from "react-native";
import CalendarStrip from "react-native-calendar-strip";
import styles from "./CalendarStrip.style";
import moment from "moment";
import "moment/locale/de";
import { useRoute } from "@react-navigation/native";
//import Appointment from "../../../models/appointment";
//import { getAppointments } from "../../../api/services/CalendarService";

const CalStrip = () => {
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

  const onPressDate = (date) => {
    setDate({ selectedDate: date }), console.log("enPressDate");
    return (
      <View>
        <Text style={styles.description}>appointment1111</Text>
      </View>
    );
  };

  return (
    <View style={styles.container}>
      <View>
        <CalendarStrip
          style={{ height: 150, paddingTop: 20, paddingBottom: 10 }}
          daySelectionAnimation={{
            type: "border",
            duration: 200,
            borderWidth: 1,
            borderHighlightColor: "black",
          }}
          calendarColor={"#E0FFFF"}
          onDateSelected={(date) => {
            setDate(moment(date).format("DD/MM/YYYY")),
              onPressDate(date),
              console.log(
                "onDateSelected: ",
                moment(date).format("DD/MM/YYYY")
              );
          }}
          startingDate={moment.date}
        />
      </View>
      <ScrollView style={styles.container}>
        <Text>hier kommen die termine hin {appointments.values}</Text>
      </ScrollView>
    </View>
  );
};
moment.locale("de");
moment().format("D MMM YY");

export default CalStrip;
