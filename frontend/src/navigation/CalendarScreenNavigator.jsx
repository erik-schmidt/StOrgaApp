import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import CalendarScreen from "../screens/Calendar/CalendarScreen";
import DrawerButton from "../components/DrawerButton";

const CalendarScreenNavigator = () => {
  const CalendarStack = createStackNavigator();
  return (
    <CalendarStack.Navigator>
      <CalendarStack.Screen
        name="Kalender"
        component={CalendarScreen}
        options={{
          headerLeft: () => <DrawerButton />
        }}
      />
    </CalendarStack.Navigator>
  );
};

export default CalendarScreenNavigator;
