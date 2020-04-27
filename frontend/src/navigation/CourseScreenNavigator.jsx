import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import DrawerButton from "../components/DrawerButton/DrawerButton";
import CourseScreen from "../screens/course/CourseScreen";
import CreateCourseModal from "../screens/course/modal/CreateCourseModal";
import AddButton from "../components/AddButton/AddButton";

const CourseScreenNavigator = ({ navigation }) => {
  const CourseStack = createStackNavigator();
  return (
    <CourseStack.Navigator mode="modal">
      <CourseStack.Screen
        name="Fächer"
        component={CourseScreen}
        options={{
          headerLeft: () => <DrawerButton />,
          headerRight: () => <AddButton onPress={() => navigation.navigate("CourseModal")} />
        }}
      />
      <CourseStack.Screen
        name="CourseModal"
        component={CreateCourseModal}
        options={{
          headerTitle: "Kurs hinzufügen",
          cardStyle: { backgroundColor: "#ffff" },
          
        }}
      />
    </CourseStack.Navigator>
  );
};

export default CourseScreenNavigator;
