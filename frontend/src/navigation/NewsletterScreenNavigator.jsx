import React from "react";
import {
  createStackNavigator,
  TransitionPresets,
} from "@react-navigation/stack";
import index from "../screens/Newsletter/index";
import DrawerButton from "../components/DrawerButton/DrawerButton";

const NewsletterScreenNavigator = ({ navigation }) => {
  const NewsletterStack = createStackNavigator();
  return (
    <NewsletterStack.Navigator
      screenOptions={() => ({
        gestureEnabled: true,
        cardOverlayEnabled: true,
        ...TransitionPresets.ModalPresentationIOS,
      })}
      mode="modal"
    >
      <NewsletterStack.Screen
        name="Newsletter"
        component={index}
        options={{
          headerLeft: () => <DrawerButton />,
        }}
      />
    </NewsletterStack.Navigator>
  );
};

export default NewsletterScreenNavigator;
