import { fetch, put, del } from "../apiService";
import { AsyncStorage } from "react-native";

export const getAllCourses = () => {
  return fetch("course/get");
};

export const getAllStudentCourses = async () => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return fetch(`course/${matrNr}/get`);
};

export const addCourse = async (course) => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return put(`course/${matrNr}/add`, course);
};

export const deleteCourse = async (courseNumber) => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return del(`course/${matrNr}/delete/${courseNumber}`);
};
