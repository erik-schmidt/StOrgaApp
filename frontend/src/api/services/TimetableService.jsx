import { fetch, post } from "../apiService";
import { AsyncStorage } from "react-native";

export const getAllCourses = async () => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return fetch(`timeTable/${matrNr}/getAllByMatriculationNumber/`);
};
export const getCoursesByCourseNumber = async (courseNumber) => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return fetch(`timeTable/${matrNr}/${courseNumber}/getAllByCourseNumber/`);
};
export const getCoursesByStartDate = async (TimeTableDateRequest) => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return post(`timeTable/${matrNr}/getByRequestObject/`, TimeTableDateRequest);
};
