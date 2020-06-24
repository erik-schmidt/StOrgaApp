import React from "react";
import {
  createStackNavigator,
  TransitionPresets,
} from "@react-navigation/stack";
import index from "../screens/Calendar/index";
import DrawerButton from "../components/DrawerButton/DrawerButton";
import AddButton from "../components/AddButton/AddButton";
import AddCalendarModal from "../screens/Calendar/AddCalendar/AddCalendarModal";
import CalendarInformationModal from "../screens/Calendar/CalendarInformationModal/CalendarInformationModal";
import CalendarMenu from "../screens/Calendar/CalendarMenu/CalendarMenu";

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
          headerStatusBarHeight: 0,
          headerTitle: "Termin hinzufügen",
          cardStyle: { backgroundColor: "#ffff" },
        }}
      />
      <CalendarStack.Screen
        name="CalendarInformationModal"
        component={CalendarInformationModal}
        options={{
          headerTitle: "Termininfo",
          headerStatusBarHeight: 0,
          cardStyle: { backgroundColor: "#ffff" },
          gestureEnabled: true,
        }}
      />
      <CalendarStack.Screen
        name="CalendarMenu"
        component={CalendarMenu}
        options={{
          headerShown: false,
          cardStyle: { backgroundColor: "transparent", opacity: 1 },
        }}
      />
    </CalendarStack.Navigator>
  );
};

export default CalendarScreenNavigator;
