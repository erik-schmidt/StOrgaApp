import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import DrawerButton from "../components/DrawerButton/DrawerButton";
import CourseScreen from "../screens/course/CourseScreen";
import CreateCourseModal from "../screens/course/modal/CreateCourseModal";
import AddButton from "../components/AddButton/AddButton";
import CourseInfoModal from "../screens/course/modal/CourseInfoModal";

const CourseScreenNavigator = ({ navigation }) => {
  const CourseStack = createStackNavigator();
  return (
    <CourseStack.Navigator mode="modal">
      <CourseStack.Screen
        name="Fächer"
        component={CourseScreen}
        options={{
          headerLeft: () => <DrawerButton />,
          headerRight: () => (
            <AddButton
              onPress={() => navigation.navigate("CreateCourseModal")}
            />
          ),
        }}
      />
      <CourseStack.Screen
        name="CreateCourseModal"
        component={CreateCourseModal}
        options={{
          headerTitle: "Kurs hinzufügen",
          cardStyle: { backgroundColor: "#ffff" },
        }}
      />
      <CourseStack.Screen
        name="CourseInfoModal"
        component={CourseInfoModal}
        options={{
          headerTitle: "Kursinfo",
          cardStyle: { backgroundColor: "#ffff" },
        }}
      />
    </CourseStack.Navigator>
  );
};

export default CourseScreenNavigator;
