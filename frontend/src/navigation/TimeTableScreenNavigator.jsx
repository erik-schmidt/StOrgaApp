import React from "react";
import {
  createStackNavigator,
  TransitionPresets,
} from "@react-navigation/stack";
import index from "../screens/Timetable/index";
import DrawerButton from "../components/DrawerButton/DrawerButton";
import TimetableInformationModal from "../screens/Timetable/TimetableInformationModal/TimetableInformationModal";

const TimetableScreenNavigator = ({ navigation }) => {
  const TimetableStack = createStackNavigator();
  return (
    <TimetableStack.Navigator
      screenOptions={() => ({
        gestureEnabled: true,
        cardOverlayEnabled: true,
        ...TransitionPresets.ModalPresentationIOS,
      })}
      mode="modal"
    >
      <TimetableStack.Screen
        name="Stundenplan"
        component={index}
        options={{
          headerLeft: () => <DrawerButton />,
        }}
      />

      <TimetableStack.Screen
        name="TimetableInformationModal"
        component={TimetableInformationModal}
        options={{
          headerTitle: "Kursinfo",
          headerStatusBarHeight: 0,
          cardStyle: { backgroundColor: "#ffff" },
          gestureEnabled: true,
        }}
      />
    </TimetableStack.Navigator>
  );
};

export default TimetableScreenNavigator;
