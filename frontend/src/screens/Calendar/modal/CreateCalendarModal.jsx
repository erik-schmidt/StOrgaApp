import React, {useState} from "react";
import { View, Button, Text } from "react-native";
import { TextInput } from "react-native-gesture-handler";
import styles from "./CreateCalendarModal.style";
import Appointment from "../../../models/appointment";
import createAppointment from "../../../api/services/CalendarService";
//import Moment from "moment";
import DatePicker from 'react-native-datepicker';

const CalendarModal = ({ navigation }) => {
  const [timeStart, setTimeStart] = useState("");
  const [timeEnd, setTimeEnd] = useState("");
  const [name, setName] = useState("");
  const [info, setInfo] = useState("");
  const [state, setState] =useState("");
  const [date, setDate] = useState("");

  //speichert in Backend
  const saveContent =() => {
    const appointment = new Appointment(date,time,name,info);
      createAppointment(appointment).then((res) => {
      console.log(res);
      console.log("speichern war erfolgreich");
      navigation.navigate("Kalender");
    })
  }

  return (
    
    <View style={styles.container}>
      <Text> 
        Termindaten eintragen: 
      </Text>
      <Text> 
        Datum: 
      </Text>
      <DatePicker
        style={{width: 200}}
        date={date}
        mode="date"
        format="YYYY-MM-DD"
        confirmBtnText="Confirm"
        cancelBtnText="Cancel"
        
        onDateChange={(date) => {setDate({date: date})}}
      />
      <Text> 
        Startzeit:  
      </Text>
      <DatePicker
          style={{width: 200}}
          date={timeStart}
          mode="time"
          format="HH:mm"
          confirmBtnText="Confirm"
          cancelBtnText="Cancel"
          minuteInterval={10}
          onDateChange={(timeStart) => {this.setTimeStart({timeStart: timeStart});}}
          //Moment einfügen 
        />
        <Text> 
        Endzeit:  
      </Text>
      <DatePicker
          style={{width: 200}}
          date={timeEnd}
          mode="time"
          format="HH:mm"
          confirmBtnText="Confirm"
          cancelBtnText="Cancel"
          minuteInterval={10}
          onDateChange={(timeEnd) => {this.setTimeEnd({timeStart: timeEnd});}}
          //Moment einfügen 
        />
      
      <TextInput style={styles.textInput} 
      placeholder="Bezeichnung"
      onChangeText={(name) => setName(name)}
        defaultValue={name}
      />
      <TextInput style={styles.textInput} 
      placeholder="Notizen"
      onChangeText={(info) => setInfo(info)}
        defaultValue={info}
      /> 
      <Button onPress={ () => {
        //saveContent();
        navigation.navigate("Kalender");
      }} 
      title="Speichern"
      /> 
    </View>
     
  );
};

export default CalendarModal;