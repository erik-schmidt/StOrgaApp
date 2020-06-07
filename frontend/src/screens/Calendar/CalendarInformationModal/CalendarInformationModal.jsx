import React, { useState } from "react";
import styles from "./CalendarInformationModal.style";
import { View, Text } from "react-native";
import { AntDesign } from "@expo/vector-icons";
import moment from "moment";

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
  const startTime = moment(appointment.entryStartDateAndTime).format("LT");
  const endTime = moment(appointment.entryFinishDateAndTime).format("LT");
  const date = moment(appointment.entryFinishDateAndTime).format("LL");

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Termin: {appointment.name}</Text>
      <Text style={styles.text}>Datum : {date} </Text>
      <Text style={styles.text}>Startzeit : {startTime} Uhr</Text>
      <Text style={styles.text}>Endzeit : {endTime} Uhr</Text>
      <Text style={styles.text}>Infos: {appointment.description}</Text>
    </View>
  );
};

export default CalendarInformationModal;
