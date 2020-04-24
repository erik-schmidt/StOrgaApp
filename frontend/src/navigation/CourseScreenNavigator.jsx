import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import DrawerButton from "../components/DrawerButton/DrawerButton";
import CourseScreen from "../screens/course";
import CreateCourseModal from "../screens/course/AddCourse/CreateCourseModal";
import AddButton from "../components/AddButton/AddButton";
import CourseInformationModal from "../screens/course/CourseInformationModal/CourseInformationModal";

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
        name="CourseInformationModal"
        component={CourseInformationModal}
        options={{
          headerTitle: "Kursinfo",
          cardStyle: { backgroundColor: "#ffff" },
        }}
      />
    </CourseStack.Navigator>
  );
};

export default CourseScreenNavigator;
