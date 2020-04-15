import React, { useEffect, Component } from "react";
import { Text, View, Button, ScrollView } from "react-native";
import styles from "./CalendarScreen.style";
import {CalendarList, LocaleConfig, Agenda} from 'react-native-calendars';


const CalendarScreen = () => {

  //const [appointments, setAppointments] = useState(0);

  /* useEffect(() => {
    console.log("Current Appointments", appointments);
  }, [appointments]); */


  LocaleConfig.locales['de'] = {
    monthNames: ['Januar','Februar','März','April','Mai','Juni','Juli','August','September','Oktober','November','Dezember'],
    monthNamesShort: ['Jan.','Febr.','März','April','Mai','Juni','Juli','Aug.','Sept.','Okt.','Nov.','Dez.'],
    dayNames: ['Sonntag','Montag','Dienstag','Mittwoch','Donnerstag','Freitag','Samstag'],
    dayNamesShort: ['So.','Mo.','Di.','Mi.','Do.','Fr.','Sa.'],
    today: 'Heute'
  };
  LocaleConfig.defaultLocale = 'de';

const appointment = {key:'appointment', color: 'red', };
const info = {key:'info', color: 'green',selectedColor: 'blue'};

return(
  <Agenda
  
  items={{
    '2020-04-22': [],
    '2020-05-23': [{time: '9:00-10:00',name: 'appointment1',info: 'item 2 - any js object', height: 100}],
    '2020-05-24': [],
    '2020-05-25': [{name: 'info',name:'appointment2',info: 'item 3 - any js object'}, {info: 'any js object'}]
  }}
  markedDates={{
    '2020-05-23': {dots: [appointment]},
    '2020-05-25': {dots: [info]}
  }}
  markingType={'multi-dot'}

  loadItemsForMonth={(month) => {console.log('trigger items loading')}}
  onDayPress={(day)=>{console.log('day pressed',day)}}
  onDayChange={(day)=>{console.log('day changed',day)}}
  
  minDate={'2020-01-01'}
  maxDate={'2020-12-31'}
  pastScrollRange={4}
  futureScrollRange={8}

  renderItem={(item) => {
    return (
      <View style={[styles.item, {height: item.height}]}>
        <Text>{item.time}</Text>
        <Text>{item.name}</Text>
        <Text>{item.info}</Text>
        </View>);}}

  renderEmptyData = {() => {
    return ( 
      <View style={styles.emptyDate}>
        <Text>No Appointments saved </Text>
      </View>);}}

/> 

);
};

export default CalendarScreen;
