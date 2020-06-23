import React, { useState } from "react";
import styles from "./CalendarInformationModal.style";
import { View, Text } from "react-native";
import { AntDesign } from "@expo/vector-icons";
import Moment from "moment";

const CalendarInformationModal = ({ navigation, route }) => {
  navigation.setOptions({
    headerRight: () => (
      <AntDesign.Button
        name="delete"
        color="black"
        backgroundColor="#ffff"
        onPress={() =>
          navigation.navigate("CalendarMenu", {
            editMode: true,
            appointment: appointment,
          })
        }
      />
    ),
  });
  const [appointment, setAppointments] = useState(route.params?.appointment);
  const startDateTime =
    appointment.entryDate + " " + appointment.entryStartTime;
  const start = Moment(startDateTime).format("LT");
  const endDateTime = appointment.entryDate + " " + appointment.entryFinishTime;
  const end = Moment(endDateTime).format("LT");
  Moment.locale("de");

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Termin: {appointment.name}</Text>
      <Text style={styles.text}>Datum : {appointment.entryDate} </Text>
      <Text style={styles.text}>Start : {start} Uhr</Text>
      <Text style={styles.text}>Ende : {end} Uhr</Text>
      <Text style={styles.text}>Infos: {appointment.description}</Text>
    </View>
  );
};

export default CalendarInformationModal;
