import React from "react";
import { View, Button, Text } from "react-native";
import { TextInput } from "react-native-gesture-handler";
import styles from "./CreateCalendarModal.style";
import Appointment from "../../../model/appointment";
import Moment from "moment";

const CalendarModal = ({ navigation }) => {
  const [text, setText] = useState("");

  //speichert in Backend
  const saveContent =() => {
    const appointment = new Appointment(text);
    createAppointment(appointment).then(() => {
      alert("speichern war erfolgreich");
      navigation.goBack();
    })
  }

  return (
    <View style={styles.container}>
      <TextInput
        style={styles.textInput}
        placeholder="Uhrzeit"
        onChangeText={(time) => setText(time)}
        defaultValue={text}
        //Moment einfügen 
      />
      <TextInput style={styles.textInput} 
      placeholder="Bezeichnung"
      />
      <TextInput style={styles.textInput} 
      placeholder="Notizen"
      />
     <Button onPress={() => navigation.navigate('Kalendar', { appointmentParam: text})} title="Speichern"/> 
  //appointment: parameter ... text: string dazu
    </View>
  );
};

export default CalendarModal;