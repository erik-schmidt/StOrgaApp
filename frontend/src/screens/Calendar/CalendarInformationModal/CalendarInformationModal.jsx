import React, { useState } from "react";
import styles from "./CalendarInformationModal.style";
import { View, Text } from "react-native";
import { FontAwesome5 } from "@expo/vector-icons";
import Toast from "../../../components/Toast/Toast";
import moment from "moment";

const CalendarInformationModal = ({ navigation, route }) => {
  navigation.setOptions({
    headerRight: () => (
      <FontAwesome5.Button
        name="edit"
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
  const [visible, setVisible] = useState(false);
  const [error, setError] = useState(false);

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Termin: {appointment.name}</Text>
      <Text style={styles.text}>
        Startzeit : {moment(appointment.start).format("LT")} Uhr
      </Text>
      <Text style={styles.text}>
        Endzeit : {moment(appointment.end).format("LT")} Uhr
      </Text>
      <Text style={styles.text}>Infos: {appointment.info}</Text>

      <Toast
        color="green"
        text="Kurs wurde erfolgreich gelöscht"
        showModal={visible}
      />
      <Toast color="red" text="Keine Verbindung zum Server" showModal={error} />
    </View>
  );
};

export default CalendarInformationModal;
