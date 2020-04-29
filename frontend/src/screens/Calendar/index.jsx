import React, { useState, useEffect } from "react";
import CalendarAgenda from "./CalendarAgenda/CalendarAgenda";
import styles from "./index.style";
import { View, Platform } from "react-native";
import WebCalendar from "./WebCalendar/WebCalendar";

const CalendarScreen = () => {
  return (
    //return Platform.OS !== "web" ? <CalendarAgenda /> : <WebCalendar />;
    <CalendarAgenda />
  );
};

export default CalendarScreen;
