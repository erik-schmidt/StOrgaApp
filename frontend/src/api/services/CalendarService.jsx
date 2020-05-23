import { fetch, put } from "../apiService";

export const getAppointments = () => {
  return fetch("calendarEntry/get");
};
export const createAppointment = (appointment) => {
  return put("calendarEntry/create", appointment);
};
export const pingCalendar = () => {
  return fetch("calendarEntry/ping");
};
