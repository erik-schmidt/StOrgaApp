import React from "react";
import {
  DrawerContentScrollView,
  DrawerItemList,
  DrawerItem,
  createDrawerNavigator,
} from "@react-navigation/drawer";
import HomeScreenNavigator from "./HomeScreenNavigator";
import CourseScreenNavigator from "./CourseScreenNavigator";
import CalendarScreenNavigator from "./CalendarScreenNavigator";
import LinkScreenNavigator from "./LinkScreenNavigator";
import GradeScreenNavigator from "./GradeScreenNavigator";
import NewsletterScreenNavigator from "./NewsletterScreenNavigator";
import TimetableScreenNavigator from "./TimeTableScreenNavigator";
import authContext from "../constants/AuthContext.jsx";
import { Dimensions, Image } from "react-native";

const DrawerNavigation = ({ navigation }) => {
  const Drawer = createDrawerNavigator();
  const { signOut } = React.useContext(authContext);
  const CustomDrawerContent = (props) => {
    return (
      <DrawerContentScrollView
        contentContainerStyle={{
          height: Math.round(Dimensions.get("window").height),
        }}
        {...props}
      >
        <Image
          style={{ width: "60%", height: "20%", alignSelf: "center" }}
          source={require("../assets/logo.png")}
        />
        <DrawerItemList {...props} />
        <DrawerItem
          style={{ position: "absolute", bottom: 0, width: "93%" }}
          label="Abmelden"
          onPress={() => signOut()}
        />
      </DrawerContentScrollView>
    );
  };
  return (
    <Drawer.Navigator
      initialRouteName="HomeViewScreen"
      drawerContentOptions={{ activeTintColor: "#66CDAA" }}
      drawerContent={(props) => <CustomDrawerContent {...props} />}
    >
      <Drawer.Screen name="Schreibtisch" component={HomeScreenNavigator} />
      <Drawer.Screen name="Stundenplan" component={TimetableScreenNavigator} />
      <Drawer.Screen name="Kalender" component={CalendarScreenNavigator} />
      <Drawer.Screen name="Vorlesungen" component={CourseScreenNavigator} />
      <Drawer.Screen name="Noten" component={GradeScreenNavigator} />
      <Drawer.Screen name="Newsletter" component={NewsletterScreenNavigator} />
      <Drawer.Screen name="Wichtige Links" component={LinkScreenNavigator} />
    </Drawer.Navigator>
  );
};

export default DrawerNavigation;
