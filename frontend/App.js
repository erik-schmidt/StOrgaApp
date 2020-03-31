import React from "react";
import { NavigationContainer } from "@react-navigation/native";
import { createDrawerNavigator } from "@react-navigation/drawer";
import HomeView from "./src/views/HomeView";
import CalenderView from "./src/views/CalenderView";

const Drawer = createDrawerNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Drawer.Navigator initialRouteName="HomeView">
        <Drawer.Screen name="Schreibtisch" component={HomeView} />
        <Drawer.Screen name="Kalender" component={CalenderView} />
        <Drawer.Screen name="Studenplan" component={HomeView} />
        <Drawer.Screen name="Wichtige Links" component={HomeView} />
        <Drawer.Screen name="Fächer" component={HomeView} />
        <Drawer.Screen name="Noten" component={HomeView} />
        <Drawer.Screen name="Lernplan" component={HomeView} />
      </Drawer.Navigator>
    </NavigationContainer>
  );
}
