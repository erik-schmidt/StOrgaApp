import React,{useState,useEffect} from "react";
import { Text, View, Button, ScrollView } from "react-native";
import styles from "./CalendarScreen.style";
import {LocaleConfig, Agenda} from 'react-native-calendars';


const CalendarScreen = ({navigation, route}) => {

  //const [appointments, setAppointments] = useState([]);
/*
  useEffect(() => {
    getAppointments().then((res)=>{
      if(res!==undefined){
        setAppointments(res.data)
      }
    })
    console.log("Current Appointments", appointments);
  }, []); 
  useEffect(() => {}, [appointments, route.params?.request]);
*/

  LocaleConfig.locales['de'] = {
    monthNames: ['Januar','Februar','März','April','Mai','Juni','Juli','August','September','Oktober','November','Dezember'],
    monthNamesShort: ['Jan.','Febr.','März','April','Mai','Juni','Juli','Aug.','Sept.','Okt.','Nov.','Dez.'],
    dayNames: ['Sonntag','Montag','Dienstag','Mittwoch','Donnerstag','Freitag','Samstag'],
    dayNamesShort: ['So.','Mo.','Di.','Mi.','Do.','Fr.','Sa.'],
    today: 'Heute'
  };
  LocaleConfig.defaultLocale = 'de';

  const termin = {key:'termin', color:'red',selectedDotColor: 'blue'};

return(
  <Agenda
  
  items={{ 
    '2020-04-22':[{timeStart: '8:00',timeEnd:'11:00',name: 'appointment1',info: 'das ist hard gecodet'}],
    '2020-04-23':[{timeStart: '10:00',timeEnd:'12:30',name: 'appointment2',info: 'das ist hard gecodet'}],
    '2020-04-22':[{timeStart: '9:00',timeEnd:'11:00',name: 'appointment3',info: 'das ist hard gecodet'}],
  }}
  //Termin markieren 
  markedDates={{
    '2020-04-22': {dots: [termin], color: 'red'},
    '2020-04-23': {dots: [termin], color: 'red'},
  }}
  markingType={'multi-dot'}

  loadItemsForMonth={(month) => {console.log('month loading')}}
  onCalendarToggled={(calendarOpened) => {console.log(calendarOpened)}}
  onDayPress={(day)=>{console.log('day pressed')}}
  onDayChange={(day)=>{console.log('day changed')}}
  
  minDate={'2020-01-01'}
  maxDate={'2020-12-31'}
  pastScrollRange={4}
  futureScrollRange={8}

  renderItem={(item) => {
    return (
      <View style={[styles.item, {height: item.height}]}>
        <Text>{item.timeStart+" - "+ item.timeEnd}</Text>
        <Text>{item.name}</Text>
        <Text>{item.info}</Text>
        </View>);
        }}

  renderEmptyData = {() => {
    return ( 
      <View style={styles.emptyDate}>
        <Text>No Appointments saved </Text>
      </View>);}}
//Longpress für bearbeiten und löschen evtl. Toast bei onClick dazu 
/> 

);
};

export default CalendarScreen;
