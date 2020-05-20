import React, { useState } from "react";
import { View, Button, Text } from "react-native";
import { TextInput } from "react-native-gesture-handler";
import styles from "./ChangeCalendarModal.style";
import Appointment from "../../../models/appointment";
//import { createAppointment } from "../../../api/services/CalendarService";
import DatePicker from "react-native-datepicker";
import AppModal from "../../../components/AppModal/AppModal";
import AppButton from "../../../components/AppButton/AppButton";

//TODO: geänderte Zeit und Datum speichern und zurück geben

const ChangeCalendarModal = ({ navigation, route }) => {
  const [timeStart, setTimeStart] = useState(new Date());
  const [timeEnd, setTimeEnd] = useState(new Date());
  const [name, setName] = useState("");
  const [info, setInfo] = useState("");
  const [date, setDate] = useState(new Date());
  const [toChangeAppointment, settoChangeAppointments] = useState(
    route.params?.toChangeAppointment
  );

  const saveContent = () => {
    const start = date + " " + timeStart;
    const end = date + " " + timeEnd;
    const appointment = new Appointment(date, start, end, name, info);

    //console.log("EntryObjekt ist: ", appointment);
    /*createAppointment(appointment).then((res) => {
      console.log(res);
      //console.log("speichern war erfolgreich");
      
    });
    */
    navigation.navigate("Kalender", { entry: appointment });
  };

  return (
    <View style={styles.container}>
      <AppModal>
        <Text style={styles.description}>Datum:</Text>
        <DatePicker
          style={styles.picker}
          date={toChangeAppointment.start}
          mode="date"
          confirmBtnText="Confirm"
          cancelBtnText="Cancel"
          onDateChange={(date) => {
            setDate(date);
          }}
        />

        <Text style={styles.description}>Startzeit:</Text>
        <DatePicker
          style={styles.picker}
          date={toChangeAppointment.start}
          mode="time"
          confirmBtnText="Confirm"
          cancelBtnText="Cancel"
          minuteInterval={10}
          onDateChange={(time) => {
            setTimeStart(time);
          }}
        />
        <Text style={styles.description}>Dauer:</Text>
        <DatePicker
          style={styles.picker}
          date={toChangeAppointment.end}
          mode="time"
          confirmBtnText="Confirm"
          cancelBtnText="Cancel"
          minuteInterval={10}
          onDateChange={(time) => {
            setTimeEnd(time);
          }}
        />

        <Text style={styles.description}>Bezeichnung:</Text>
        <TextInput
          style={styles.textInput}
          onChangeText={(name) => setName(name)}
          value={toChangeAppointment.name}
        />
        <Text style={styles.description}>Notizen:</Text>
        <TextInput
          style={styles.textInput}
          onChangeText={(info) => setInfo(info)}
          value={toChangeAppointment.info}
        />
        <AppButton
          onPress={() => {
            saveContent();
          }}
          text="Speichern"
        />
      </AppModal>
    </View>
  );
};

export default ChangeCalendarModal;
