import React from "react";
import {
  createStackNavigator,
  TransitionPresets,
} from "@react-navigation/stack";
import index from "../screens/Newsletter/index";
import DrawerButton from "../components/DrawerButton/DrawerButton";
import NewsInformationModal from "../screens/Newsletter/NewsInformationModal/NewsInformationModal";

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
      <NewsletterStack.Screen
        name="NewsInformationModal"
        component={NewsInformationModal}
        options={{
          headerTitle: "Newsinfo",
          headerStatusBarHeight: 0,
          cardStyle: { backgroundColor: "#ffff" },
          gestureEnabled: true,
        }}
      />
    </NewsletterStack.Navigator>
  );
};

export default NewsletterScreenNavigator;
