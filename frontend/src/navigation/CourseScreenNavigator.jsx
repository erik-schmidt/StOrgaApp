import React from "react";
import {
  createStackNavigator,
  TransitionPresets,
} from "@react-navigation/stack";
import DrawerButton from "../components/DrawerButton/DrawerButton";
import AddCourseModal from "../screens/Course/AddCourse/AddCourseModal";
import CourseListTabNavigator from "./CourseListTabNavigator";
import AddButton from "../components/AddButton/AddButton";
import CourseInformationModal from "../screens/Course/CourseInformationModal/CourseInformationModal";
import CourseMenu from "../screens/Course/CourseMenu/CourseMenu";

const CourseScreenNavigator = ({ navigation }) => {
  const CourseStack = createStackNavigator();
  return (
    <CourseStack.Navigator
      mode="modal"
      screenOptions={({ route, navigation }) => ({
        gestureEnabled: true,
        cardOverlayEnabled: true,
        ...TransitionPresets.ModalPresentationIOS,
      })}
    >
      <CourseStack.Screen
        name="Fächer"
        component={CourseListTabNavigator}
        options={{
          headerTitle: "Vorlesungen",
          headerLeft: () => <DrawerButton />,
          headerRight: () => (
            <AddButton onPress={() => navigation.navigate("AddCourseModal")} />
          ),
        }}
      />
      <CourseStack.Screen
        name="AddCourseModal"
        component={AddCourseModal}
        options={{
          cardStyle: { backgroundColor: "transparent" },
          headerShown: false,
          ...TransitionPresets.ModalPresentationIOS,
        }}
      />
      <CourseStack.Screen
        name="CourseInformationModal"
        component={CourseInformationModal}
        options={{
          headerTitle: "Vorlesungsinformation",
          headerStatusBarHeight: 0,
          cardStyle: { backgroundColor: "#ffff" },
          gestureEnabled: true,
          ...TransitionPresets.ModalPresentationIOS,
        }}
      />
      <CourseStack.Screen
        name="CourseMenu"
        component={CourseMenu}
        options={{
          headerShown: false,
          cardStyle: { backgroundColor: "transparent", opacity: 1 },
        }}
      />
    </CourseStack.Navigator>
  );
};

export default CourseScreenNavigator;
