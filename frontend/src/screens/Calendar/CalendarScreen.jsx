import React, { useEffect, Component } from "react";
import { Text, View, Button, ScrollView } from "react-native";
import styles from "./CalendarScreen.style";
import {CalendarList, LocaleConfig, Agenda} from 'react-native-calendars';



const CalendarScreen = ({ navigation }) => {

  LocaleConfig.locales['de'] = {
    monthNames: ['Januar','Februar','März','April','Mai','Juni','Juli','August','September','Oktober','November','Dezember'],
    monthNamesShort: ['Jan.','Febr.','März','April','Mai','Juni','Juli','Aug.','Sept.','Okt.','Nov.','Dez.'],
    dayNames: ['Sonntag','Montag','Dienstag','Mittwoch','Donnerstag','Freitag','Samstag'],
    dayNamesShort: ['Son.','Mon.','Di.','Mi.','Do.','Fr.','Sam.'],
    today: 'Heute'
  };
  LocaleConfig.defaultLocale = 'de';

return(
      
<Agenda
  // The list of items that have to be displayed in agenda. If you want to render item as empty date
  // the value of date key has to be an empty array []. If there exists no value for date key it is
  // considered that the date in question is not yet loaded
  items={{
    '2020-05-22': [{name: 'item 1 - any js object'}],
    '2020-05-23': [{name: 'item 2 - any js object', height: 80}],
    '2020-05-24': [],
    '2020-05-25': [{name: 'item 3 - any js object'}, {name: 'any js object'}]
  }}
  // Callback that gets called when items for a certain month should be loaded (month became visible)
  loadItemsForMonth={(month) => {console.log('trigger items loading')}}
  // Callback that gets called on day press
  onDayPress={(day)=>{console.log('day pressed',day)}}
  // Minimum date that can be selected, dates before minDate will be grayed out. Default = undefined
  minDate={'2020-01-01'}
  // Maximum date that can be selected, dates after maxDate will be grayed out. Default = undefined
  maxDate={'2020-12-31'}
  // Max amount of months allowed to scroll to the past. Default = 50
  pastScrollRange={4}
  // Max amount of months allowed to scroll to the future. Default = 50
  futureScrollRange={8}
  // Specify how each item should be rendered in agenda
  renderItem={(item, firstItemInDay) => {return (<View />);}}
  // Specify how each date should be rendered. day can be undefined if the item is not first in that day.
  renderDay={(day, item) => {return (<View />);}}
  // Specify how empty date content with no items should be rendered
  renderEmptyDate={() => {return (<View />);}}
  // Specify what should be rendered instead of ActivityIndicator
  renderEmptyData = {() => {return (<View />);}}
  // Specify your item comparison function for increased performance
  rowHasChanged={(r1, r2) => {return r1.text !== r2.text}}
  // Agenda theme
  theme={{
    agendaDayTextColor: 'yellow',
    agendaDayNumColor: 'green',
    agendaTodayColor: 'red',
    agendaKnobColor: 'blue'
  }}
  // Agenda container style
  style={{}}
/>

);

};


export default CalendarScreen;
