import { fetch, post } from "../apiService";
import { AsyncStorage } from "react-native";

export const getAppointments = async () => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return fetch(`calendarEntry/${matrNr}/get/`);
};
export const createAppointment = async (appointment) => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return post(`calendarEntry/${matrNr}/create/`, appointment);
};
export const pingCalendar = () => {
  return fetch("calendarEntry/ping");
};
export const deleteCalendar = async (appointment) => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return post(`calendarEntry/${matrNr}/delete/`, appointment);
};
export const getAllAppointments = () => {
  return fetch("calendarEntry/getAll");
};
