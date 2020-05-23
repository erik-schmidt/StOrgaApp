import { fetch, put, del } from "../apiService";
import { AsyncStorage } from "react-native";

export const getAllCourses = () => {
  return fetch("course/get");
};

export const getAllStudentCourses = async () => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return fetch(`course/getStudentsCourses/${matrNr}`);
}

export const createCourse = (course) => {
  return put("course/create", course);
};

export const addCourse = async (course) => {
  const matrNr = await AsyncStorage.getItem('matrNr');
  return put(`course/add/${matrNr}`, course);
};

export const deleteCourse = async (courseNumber) => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return del(`course/delete/${matrNr}/${courseNumber}`);
}
