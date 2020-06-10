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
export const getWeeklyAppointments = async (date) => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return fetch(`calendarEntry/${matrNr}/getWeek/`, date);
};
export const deleteCalendar = async (id) => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return post(`calendarEntry/${matrNr}/delete/`, id);
};
export const getAllAppointments = () => {
  return fetch("calendarEntry/getAll");
};
