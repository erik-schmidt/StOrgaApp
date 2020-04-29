import React, { useState } from "react";
import { Calendar, momentLocalizer } from "react-big-calendar";
import { View } from "react-native";
import moment from "moment";
import styles from "./WebCalendar.style";

const WebCalendar = () => {
  const [event, setEvent] = useState([]);

  const localizer = momentLocalizer(moment);

  return (
    <View>
      <Calendar
        localizer={localizer}
        onView={() => console.log()}
        events={event}
        style={{ ...styles, height: 400, backgroundColor: "white" }}
      />
    </View>
  );
};
export default WebCalendar;
