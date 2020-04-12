import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import DrawerButton from "../components/DrawerButton/DrawerButton";
import CourseScreen from "../screens/course/CourseScreen";

const CourseScreenNavigator = ({navigation}) => {
  const CourseStack = createStackNavigator();
  return (
    <CourseStack.Navigator>
      <CourseStack.Screen
        name="Fächer"
        component={CourseScreen}
        options={{
          headerLeft: () => <DrawerButton />
        }}
      />
    </CourseStack.Navigator>
  );
};

export default CourseScreenNavigator;
