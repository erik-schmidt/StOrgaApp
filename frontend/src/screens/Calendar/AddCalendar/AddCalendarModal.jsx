import React, { useState } from "react";
import { View, Button, Text } from "react-native";
import { TextInput, TouchableOpacity } from "react-native-gesture-handler";
import styles from "./AddCalendarModal.style";
import Appointment from "../../../models/appointment";
//import { createAppointment } from "../../../api/services/CalendarService";
import DatePicker from "react-native-datepicker";
import AppModal from "../../../components/AppModal/AppModal";

const AddCalendarModal = ({ navigation, route }) => {
  const [timeStart, setTimeStart] = useState(new Date());
  const [timeEnd, setTimeEnd] = useState(new Date());
  const [name, setName] = useState("");
  const [info, setInfo] = useState("");
  const [date, setDate] = useState(new Date());
  const entry = {};

  const saveContent = () => {
    const appointment = new Appointment(date, timeStart, timeEnd, name, info);
    entry[date] = [appointment];
    //console.log(entry);
    /*createAppointment(appointment).then((res) => {
      console.log(res);
      //console.log("speichern war erfolgreich");
      
    });
    */
    navigation.navigate("Kalender", { entry: entry });
  };

  return (
    <View style={styles.container}>
      <AppModal header="Termin hinzufügen">
        <Text style={styles.description}>Datum:</Text>
        <DatePicker
          style={styles.picker}
          date={date}
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
          date={timeStart}
          mode="time"
          confirmBtnText="Confirm"
          cancelBtnText="Cancel"
          minuteInterval={10}
          onDateChange={(time) => {
            setTimeStart(time);
          }}
        />
        <Text style={styles.description}>Endzeit:</Text>
        <DatePicker
          style={styles.picker}
          date={timeEnd}
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
          }}
          title="Speichern"
        />
      </AppModal>
    </View>
  );
};

export default AddCalendarModal;
