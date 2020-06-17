import React from "react";
import { createMaterialTopTabNavigator } from "@react-navigation/material-top-tabs";
import CourseScreen from "../screens/Course";
import DrawerButton from "../components/DrawerButton/DrawerButton";
import AddButton from "../components/AddButton/AddButton";
import MajorStudiesList from "../screens/Course/CourseList/MajorStudiesList";
import KindOfSubjectList from "../screens/Course/CourseList/KindOfSubjectList";

const Tab = createMaterialTopTabNavigator();

const CourseListTabNavigator = ({ navigation }) => {
  return (
    <Tab.Navigator>
      <Tab.Screen name="Alle" component={CourseScreen} />
      <Tab.Screen name="Grund-/Hauptstudium" component={MajorStudiesList} />
      <Tab.Screen name="Wahl-/Pflichtfächer" component={KindOfSubjectList} />
    </Tab.Navigator>
  );
};

export default CourseListTabNavigator;
