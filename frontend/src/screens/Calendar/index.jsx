import React, { useState, useEffect } from "react";
import CalendarAgenda from "./CalendarAgenda/CalendarAgenda";
import styles from "./index.style";
import { View, Platform } from "react-native";
import WebCalendar from "./WebCalendar/WebCalendar";

const CalendarScreen = () => {

    const test =()=>{
        return(
        Platform.OS !== "web" ? <CalendarAgenda /> : <WebCalendar />
        )
    }

  return (
    Platform.OS !== "web" ? <CalendarAgenda /> : <WebCalendar />
  );
};

export default CalendarScreen;
