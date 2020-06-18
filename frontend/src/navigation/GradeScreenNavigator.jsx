import React from "react";
import {
  createStackNavigator,
  TransitionPresets,
} from "@react-navigation/stack";
import GradeScreen from "../screens/Grade";
import DrawerButton from "../components/DrawerButton/DrawerButton";
import GradeMenu from "../screens/Grade/GradeMenu/GradeMenu";

const GradeScreenNavigator = ({ navigation }) => {
  const GradeStack = createStackNavigator();
  return (
    <GradeStack.Navigator
      mode="modal"
      screenOptions={() => ({
        gestureEnabled: true,
        cardOverlayEnabled: true,
        ...TransitionPresets.ModalPresentationIOS,
      })}
    >
      <GradeStack.Screen
        name="Noten"
        component={GradeScreen}
        options={{
          headerLeft: () => <DrawerButton />,
        }}
      />
      <GradeStack.Screen
        name="GradeMenu"
        component={GradeMenu}
        options={{
          headerShown: false,
          cardStyle: { backgroundColor: "transparent", opacity: 1 },
        }}
      />
    </GradeStack.Navigator>
  );
};

export default GradeScreenNavigator;

