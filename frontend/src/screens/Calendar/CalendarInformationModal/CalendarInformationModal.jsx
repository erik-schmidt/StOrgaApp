import React, { useState } from "react";
import styles from "./CalendarInformationModal.style";
import { View, Text } from "react-native";
import { AntDesign } from "@expo/vector-icons";
import "moment/locale/de";

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

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Termin: {appointment.name}</Text>
      <Text style={styles.text}>Datum : {appointment.entryDate} </Text>
      <Text style={styles.text}>Start : {appointment.entryStartTime} Uhr</Text>
      <Text style={styles.text}>Ende : {appointment.entryFinishTime} Uhr</Text>
      <Text style={styles.text}>Infos: {appointment.description}</Text>
    </View>
  );
};

export default CalendarInformationModal;
