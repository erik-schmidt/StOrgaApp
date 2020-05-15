import React, { useState } from "react";
import { View, TextInput } from "react-native";
import styles from "./CalendarMenu.style";
import AppButton from "../../../components/AppButton/AppButton";
//import { deleteCalendar} from "../../../api/services/CalendarService";
import AppModal from "../../../components/AppModal/AppModal";

const CalendarMenu = ({ navigation, route }) => {
  const [appointment, setAppointments] = useState(route.params?.appointment);

  /*const onDeleteCalendar = () => {
    deleteCalendar(appointment.number)
      .then((res) => {
        if (res != undefined) {
          setVisible(true);
          setTimeout(() => {
            setVisible(false);
            navigation.navigate("Fächer", { deleteCalendar: true });
          }, 1000);
        } else {
          throw new Error();
        }
      })
      .catch((err) => {
        setError(true);
        setTimeout(() => {
          setError(false);
        }, 3000);
      });
  };*/

  return (
    <View style={styles.container}>
      <View>
        <AppModal header="Termin bearbeiten" description={appointment.name}>
          <AppButton onPress={() => navigation.pop()} text="bearbeiten" />
          <AppButton
            color="red"
            onPress={() => navigation.pop()}
            text="löschen"
          />
          <AppButton onPress={() => navigation.pop()} text="abbrechen" />
        </AppModal>
      </View>
    </View>
  );
};

export default CalendarMenu;
