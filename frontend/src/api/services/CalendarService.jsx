import { fetch, put } from "../apiService";

export const getAppointments = async () => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return fetch("calendarEntry/get/" + matrNr);
};
export const createAppointment = async (appointment) => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return put("calendarEntry/create/" + matrNr, appointment);
};
export const pingCalendar = () => {
  return fetch("calendarEntry/ping");
};
