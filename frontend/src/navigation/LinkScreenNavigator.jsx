import React from "react";
import {
  createStackNavigator,
  TransitionPresets,
} from "@react-navigation/stack";
import LinkScreen from "../screens/Links";
import DrawerButton from "../components/DrawerButton/DrawerButton";
import AddButton from "../components/AddButton/AddButton";

const LinkScreenNavigator = ({ navigation }) => {
  const LinkStack = createStackNavigator();
  return (
    <LinkStack.Navigator
      mode="modal"
      screenOptions={({ route, navigation }) => ({
        gestureEnabled: true,
        cardOverlayEnabled: true,
        ...TransitionPresets.ModalPresentationIOS,
      })}
    >
      <LinkStack.Screen
        name="LinkScreen"
        component={LinkScreen}
        options={{
          headerLeft: () => <DrawerButton />,
          headerRight: () => <AddButton />,
        }}
      />
    </LinkStack.Navigator>
  );
};

export default LinkScreenNavigator;
