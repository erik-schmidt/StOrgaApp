import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import HomeScreen from "../screen/home/HomeScreen";
import DrawerButton from "../components/DrawerButton";

const HomeScreenNavigator = () => {
  const HomeStack = createStackNavigator();
  return (
    <HomeStack.Navigator>
      <HomeStack.Screen
        name="Home"
        component={HomeScreen}
        options={{
          headerLeft: () => <DrawerButton />
        }}
      />
    </HomeStack.Navigator>
  );
};

export default HomeScreenNavigator;
