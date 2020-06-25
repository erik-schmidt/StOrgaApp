import React from "react";
import {
  createStackNavigator,
  TransitionPresets,
} from "@react-navigation/stack";
import LinkScreen from "../screens/Links";
import DrawerButton from "../components/DrawerButton/DrawerButton";
import AddButton from "../components/AddButton/AddButton";
import LinkMenu from "../screens/Links/LinkMenu/LinkMenu";
import AddLink from "../screens/Links/AddLink/AddLink";

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
          headerTitle: "Link Sammlung",
          headerLeft: () => <DrawerButton />,
          headerRight: () => (
            <AddButton onPress={() => navigation.navigate("AddLink")} />
          ),
        }}
      />
      <LinkStack.Screen
        name="LinkMenu"
        component={LinkMenu}
        options={{
          headerShown: false,
          cardStyle: { backgroundColor: "transparent" },
          ...TransitionPresets.ModalPresentationIOS,
        }}
      />
      <LinkStack.Screen
        name="AddLink"
        component={AddLink}
        options={{
          headerShown: false,
          cardStyle: { backgroundColor: "transparent" },
          ...TransitionPresets.ModalPresentationIOS,
        }}
      />
    </LinkStack.Navigator>
  );
};

export default LinkScreenNavigator;
