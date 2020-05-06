import React from "react";
import {
  createStackNavigator,
  TransitionPresets,
} from "@react-navigation/stack";
import index from "../screens/Calendar/index";
import DrawerButton from "../components/DrawerButton/DrawerButton";
import AddButton from "../components/AddButton/AddButton";
import AddCalendarModal from "../screens/Calendar/AddCalendar/AddCalendarModal";

const CalendarScreenNavigator = ({ navigation }) => {
  const CalendarStack = createStackNavigator();
  return (
    <CalendarStack.Navigator
      screenOptions={() => ({
        gestureEnabled: true,
        cardOverlayEnabled: true,
        ...TransitionPresets.ModalPresentationIOS,
      })}
      mode="modal"
    >
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
          cardStyle: { backgroundColor: "transparent" },
          headerShown: false,
        }}
      />
    </CalendarStack.Navigator>
  );
};

export default CalendarScreenNavigator;
