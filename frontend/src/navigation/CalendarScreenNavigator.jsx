import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import CalendarScreen from "../screens/Calendar/CalendarScreen";
import DrawerButton from "../components/DrawerButton/DrawerButton";
import AddButton from "../components/AddButton/AddButton";
import AddCalendarModal from "../screens/Calendar/AddCalendar/AddCalendarModal";

const CalendarScreenNavigator = ({navigation}) => {
  const CalendarStack = createStackNavigator();
  return (
    <CalendarStack.Navigator>
      <CalendarStack.Screen
        name="Kalender"
        component={CalendarScreen}
        options={{
          headerLeft: () => <DrawerButton />,
          headerRight: () => <AddButton onPress={() => navigation.navigate("AddCalendarModal")} />
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
