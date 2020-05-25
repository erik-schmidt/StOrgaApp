import { fetch, put } from "../apiService";

export const getAppointments = async () => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return fetch("calendarEntry/get" + matrNr);
};
export const createAppointment = (appointment) => {
  return put("calendarEntry/create", appointment);
};
export const pingCalendar = () => {
  return fetch("calendarEntry/ping");
};
