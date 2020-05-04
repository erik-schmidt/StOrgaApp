import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import index from "../screens/calendar/index";
import DrawerButton from "../components/DrawerButton/DrawerButton";
import AddButton from "../components/AddButton/AddButton";
<<<<<<< HEAD
import AddCalendarModal from "../screens/calendar/addCalendar/AddCalendarModal";
=======
import AddCalendarModal from "../screens/Calendar/AddCalendar/AddCalendarModal";
>>>>>>> 1b405c78747f2b422fb747c187edda6098fcaef8

const CalendarScreenNavigator = ({ navigation }) => {
  const CalendarStack = createStackNavigator();
  return (
    <CalendarStack.Navigator>
      <CalendarStack.Screen
        name="Kalender"
        component={index}
        options={{
          headerLeft: () => <DrawerButton />,
          headerRight: () => (
            <AddButton
              onPress={() => navigation.navigate("AddCalendarModal")}
            />
          ),
        }}
      />
      <CalendarStack.Screen
        name="AddCalendarModal"
        component={AddCalendarModal}
        options={{
          headerTitle: "Termin hinzufügen",
          cardStyle: { backgroundColor: "#ffff" },
        }}
      />
    </CalendarStack.Navigator>
  );
};

export default CalendarScreenNavigator;
