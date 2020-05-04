import React, { useState } from "./node_modules/react";
import { View, Button, Text } from "react-native";
import { TextInput } from "react-native-gesture-handler";
import styles from "./AddCalendarModal.style";
import Appointment from "../../../models/appointment";
import { createAppointment } from "../../../api/services/CalendarService";
import moment from "./node_modules/moment";
import DatePicker from "./node_modules/react-native-datepicker";

const AddCalendarModal = ({ navigation }) => {
  const [timeStart, setTimeStart] = useState("");
  const [timeEnd, setTimeEnd] = useState("");
  const [name, setName] = useState("");
  const [info, setInfo] = useState("");
  const [date, setDate] = useState("");

  //speichert in Backend
  const saveContent = () => {
    const appointment = new Appointment(date, timeStart, timeEnd, name, info);
    createAppointment(appointment).then((res) => {
      console.log(res);
      console.log("speichern war erfolgreich");
      navigation.navigate("Kalender");
    });
  };

  return (
    <View style={styles.container}>
      <Text style={styles.description}>Datum:</Text>
      <DatePicker
        style={styles.picker}
        date={date}
        mode="date"
        //format="YYYY-MM-DD"
        confirmBtnText="Confirm"
        cancelBtnText="Cancel"
        onDateChange={(date) => {
          setDate({ date: moment(date).format("YYYY-MM-DD") });
        }}
      />
      <Text style={styles.description}>Startzeit:</Text>
      <DatePicker
        style={styles.picker}
        date={timeStart}
        mode="time"
        //format="HH:mm"
        confirmBtnText="Confirm"
        cancelBtnText="Cancel"
        minuteInterval={10}
        onDateChange={(timeStart) => {
          setTimeStart({ timeStart: moment(timeStart).format("HH:mm") });
        }}
      />
      <Text style={styles.description}>Endzeit:</Text>
      <DatePicker
        style={styles.picker}
        date={timeEnd}
        mode="time"
        //format="HH:mm"
        confirmBtnText="Confirm"
        cancelBtnText="Cancel"
        minuteInterval={10}
        onDateChange={(timeEnd) => {
          setTimeEnd({ timeEnd: moment(timeEnd).format("HH:mm") });
        }}
      />
      <Text style={styles.description}>Bezeichnung:</Text>
      <TextInput
        style={styles.textInput}
        placeholder="Bezeichnung "
        onChangeText={(name) => setName(name)}
        defaultValue={name}
      />
      <Text style={styles.description}>Notizen:</Text>
      <TextInput
        style={styles.textInput}
        placeholder="Notizen "
        onChangeText={(info) => setInfo(info)}
        defaultValue={info}
      />
      <Button
        onPress={() => {
          saveContent();
          navigation.navigate("Kalender");
        }}
        title="Speichern"
      />
    </View>
  );
};

export default AddCalendarModal;
