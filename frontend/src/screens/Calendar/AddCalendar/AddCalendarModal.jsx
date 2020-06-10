import React, { useState } from "react";
import { View, Button, Text } from "react-native";
import { TextInput } from "react-native-gesture-handler";
import styles from "./AddCalendarModal.style";
import { createAppointment } from "../../../api/services/CalendarService";
import DatePicker from "react-native-datepicker";
import AppModal from "../../../components/AppModal/AppModal";
import AppButton from "../../../components/AppButton/AppButton";
import moment from "moment";
import "moment/locale/de";

const AddCalendarModal = ({ navigation, route }) => {
  const [timeStart, setTimeStart] = useState(new Date());
  const [timeEnd, setTimeEnd] = useState(new Date());
  const [name, setName] = useState("");
  const [info, setInfo] = useState("");
  const [date, setDate] = useState(new Date());

  const saveContent = () => {
    createAppointment({
      name: name,
      entryStartTime: timeStart,
      entryFinishTime: timeEnd,
      entryDate: date,
      description: info,
    }).then((res) => {
      console.log(res);
      console.log("speichern war erfolgreich");
    });
  };

  return (
    <View style={styles.container}>
      <AppModal>
        <Text style={styles.description}>Datum:</Text>
        <DatePicker
          style={styles.picker}
          date={date}
          mode="date"
          format="YYYY-MM-DD"
          confirmBtnText="OK"
          cancelBtnText="Abbrechen"
          onDateChange={(date) => {
            console.log("date: " + date);
            setDate(date);
          }}
        />

        <Text style={styles.description}>Start:</Text>
        <DatePicker
          style={styles.picker}
          date={timeStart}
          mode="time"
          format="hh:mm"
          confirmBtnText="OK"
          cancelBtnText="Abbrechen"
          minuteInterval={10}
          onDateChange={(time) => {
            console.log("timeStart: " + time);
            setTimeStart(time);
          }}
        />
        <Text style={styles.description}>Ende:</Text>
        <DatePicker
          style={styles.picker}
          date={timeEnd}
          mode="time"
          format="hh:mm"
          confirmBtnText="OK"
          cancelBtnText="Abbrechen"
          minuteInterval={10}
          onDateChange={(time) => {
            console.log("timeEnd " + time);
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

        <AppButton
          onPress={() => {
            saveContent();
            navigation.navigate("Kalender");
          }}
          text="Speichern"
        />
      </AppModal>
    </View>
  );
};

export default AddCalendarModal;
