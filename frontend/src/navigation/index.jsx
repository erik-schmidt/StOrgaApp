import React from "react";
import { createDrawerNavigator } from "@react-navigation/drawer";
import HomeScreenNavigator from './HomeScreenNavigator';
import CalendarScreenNavigator from './CalendarScreenNavigator';

const DrawerNavigation = () => {
  const Drawer = createDrawerNavigator();
  return (
    <Drawer.Navigator initialRouteName="HomeViewScreen">
      <Drawer.Screen name="Schreibtisch" component={HomeScreenNavigator} />
      <Drawer.Screen name="Kalender" component={CalendarScreenNavigator} />
      <Drawer.Screen name="Studenplan" component={HomeScreenNavigator} />
      <Drawer.Screen name="Wichtige Links" component={HomeScreenNavigator} />
      <Drawer.Screen name="Fächer" component={HomeScreenNavigator} />
      <Drawer.Screen name="Noten" component={HomeScreenNavigator} />
      <Drawer.Screen name="Lernplan" component={HomeScreenNavigator} />
    </Drawer.Navigator>
  );
};

export default DrawerNavigation;
