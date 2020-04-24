import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import CalendarScreen from "../screens/Calendar/CalendarScreen";
import DrawerButton from "../components/DrawerButton";
import AddButton from "../components/AddButton/AddButton";
import CreateCalendarModal from "../screens/Calendar/modal/CreateCalendarModal";

const CalendarScreenNavigator = ({navigation}) => {
  const CalendarStack = createStackNavigator();
  return (
    <CalendarStack.Navigator>
      <CalendarStack.Screen
        name="Kalender"
        component={CalendarScreen}
        options={{
          headerLeft: () => <DrawerButton />,
          headerRight: () => <AddButton onPress={() => navigation.navigate("CreateCalendarModal")} />
        }}
      />
      <CalendarStack.Screen
        name="CreateCalendarModal"
        component={CreateCalendarModal}
        options={{
          headerTitle: "Termin hinzufügen",
          cardStyle: { backgroundColor: "#ffff" },
          
        }}
      />
    </CalendarStack.Navigator>
  );
};

export default CalendarScreenNavigator;
