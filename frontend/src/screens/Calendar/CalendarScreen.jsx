import React, { useEffect, Component } from "react";
import { Text, View, Button, ScrollView } from "react-native";
import styles from "./CalendarScreen.style";
import {Calendar} from 'react-native-calendars';
import CalendarView from './CalendarView';
import CalendarAgenda from './CalendarAgenda';


const CalendarScreen = ({ navigation }) => {

return(
  <View style={styles.container}> 
      <Text> "KalenderSeite " </Text>
      <CalendarView/>
      <CalendarAgenda/>
    </View>
);

};


export default CalendarScreen;
