import React from "react";
import { createDrawerNavigator } from "@react-navigation/drawer";
import HomeScreenNavigator from "./HomeScreenNavigator";
import CourseScreenNavigator from "./CourseScreenNavigator";
import CalendarScreenNavigator from "./CalendarScreenNavigator";
import LinkScreenNavigator from "./LinkScreenNavigator";
import GradeScreenNavigator from "./GradeScreenNavigator";
import NewsletterScreenNavigator from "./NewsletterScreenNavigator";
import TimetableScreenNavigator from "./TimeTableScreenNavigator";

const DrawerNavigation = () => {
  const Drawer = createDrawerNavigator();
  return (
    <Drawer.Navigator initialRouteName="HomeViewScreen">
      <Drawer.Screen name="Schreibtisch" component={HomeScreenNavigator} />
      <Drawer.Screen name="Stundenplan" component={TimetableScreenNavigator} />
      <Drawer.Screen name="Kalender" component={CalendarScreenNavigator} />
      <Drawer.Screen name="Vorlesungen" component={CourseScreenNavigator} />
      <Drawer.Screen name="Noten" component={GradeScreenNavigator} />
      <Drawer.Screen name="Newsletter" component={NewsletterScreenNavigator} />
      <Drawer.Screen name="Wichtige Links" component={HomeScreenNavigator} />
    </Drawer.Navigator>
  );
};

export default DrawerNavigation;
