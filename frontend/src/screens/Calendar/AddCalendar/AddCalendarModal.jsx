import React, { useState } from "react";
import { Text, ScrollView, View, KeyboardAvoidingView } from "react-native";
import { TextInput } from "react-native-gesture-handler";
import styles from "./AddCalendarModal.style";
import { createAppointment } from "../../../api/services/CalendarService";
import DatePicker from "react-native-datepicker";
import AppModal from "../../../components/AppModal/AppModal";
import AppButton from "../../../components/AppButton/AppButton";
import moment from "moment";
import "moment/locale/de";

const AddCalendarModal = ({ navigation }) => {
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
    });
  };

  return (
    <KeyboardAvoidingView
      enabled={true}
      behavior={Platform.OS === "ios" ? "padding" : null}
      keyboardVerticalOffset={Platform.OS === "ios" ? 100 : 0}
      style={{
        flex: 1,
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        alignSelf: "center",
      }}
    >
      <ScrollView>
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
      </ScrollView>
    </KeyboardAvoidingView>
  );
};

export default AddCalendarModal;
