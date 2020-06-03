import { fetch, put, del } from "../apiService";
import { AsyncStorage } from "react-native";

export const getGrades = async () => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return fetch(`grade/${matrNr}/get`);
};

export const addGrade = async (gradeCourse) => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return put(`grade/${matrNr}/add`, gradeCourse);
};

export const getAverage = async () => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return fetch(`grade/${matrNr}/getAverage`);
};

export const deleteGrade = async (number) => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return del(`grade/${matrNr}/delete/${number}`);
};
