import React, { useState } from "react";
import { View, TextInput, Text } from "react-native";
import styles from "./CalendarMenu.style";
import AppButton from "../../../components/AppButton/AppButton";
import { deleteCalendar } from "../../../api/services/CalendarService";
import AppModal from "../../../components/AppModal/AppModal";
import * as HttpStatus from "http-status-codes";
import AuthContext from "../../../constants/AuthContext";

//TODO: Kalender löschen

const CalendarMenu = ({ navigation, route }) => {
  const [appointment, setAppointments] = useState(route.params?.appointment);
  const { signOut } = React.useContext(AuthContext);

  const onDeleteCalendar = () => {
    deleteCalendar(appointment.number)
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          navigation.navigate("Kalender", { calendarDeleted: true });
        } else if (res.status === HttpStatus.UNAUTHORIZED) {
          signOut();
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
  };

  return (
    <View style={styles.container}>
      <View>
        <AppModal header="Termin löschen?" description={appointment.name}>
          <AppButton
            color="red"
            onPress={() => onDeleteCalendar}
            text="löschen"
          />
          <AppButton onPress={() => navigation.pop()} text="abbrechen" />
        </AppModal>
      </View>
    </View>
  );
};

export default CalendarMenu;
