import React from "react";
import { StyleSheet, Text, View, Button } from "react-native";
import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import { createDrawerNavigator } from "@react-navigation/drawer";

const Home = ({ navigation }) => {
  return (
    <View style={styles.container}>
      <Text>Open up App.js to start working on your app!</Text>
      <Button title="Button" onPress={() => navigation.navigate("Kalender")} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center"
  }
});

// const BottomTab = createBottomTabNavigator();

// const Root = () => {
//   return (
//     <Tab.Navigator>
//       <Tab.Screen name="Schreibtisch" component={Home} />
//       <Tab.Screen name="Kalender" component={CalenderScreen} />
//       <Tab.Screen name="Studenplan" component={Home}/>
//       <Tab.Screen name="Wichtige Links" component={Home}/>
//       <Tab.Screen name="Fächer" component={Home}/>
//       <Tab.Screen name="Noten" component={Home}/>
//       <Tab.Screen name="Lernplan" component={Home}/>
//     </Tab.Navigator>
//   );
// }

const Stack = createStackNavigator();

const Root = () => {
  return (
    <Stack.Navigator>
      <Stack.Screen name="Schreibtisch" component={Home} />
      <Stack.Screen name="Kalender" component={Home} />
      <Stack.Screen name="Studenplan" component={Home} />
      <Stack.Screen name="Wichtige Links" component={Home} />
      <Stack.Screen name="Fächer" component={Home} />
      <Stack.Screen name="Noten" component={Home} />
      <Stack.Screen name="Lernplan" component={Home} />
    </Stack.Navigator>
  );
};

const Drawer = createDrawerNavigator();

export default function Menu() {
  return (
    <NavigationContainer>
      <Drawer.Navigator initialRouteName="Root">
        <Drawer.Screen name="Schreibtisch" component={Root} />
        <Drawer.Screen name="Kalender" component={Root} />
        <Drawer.Screen name="Studenplan" component={Home} />
        <Drawer.Screen name="Wichtige Links" component={Home} />
        <Drawer.Screen name="Fächer" component={Home} />
        <Drawer.Screen name="Noten" component={Home} />
        <Drawer.Screen name="Lernplan" component={Home} />
      </Drawer.Navigator>
    </NavigationContainer>
  );
}
