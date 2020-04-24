import {fetch, put} from '../apiService';

 export const getAppointments = (param) => {
    return fetch('calendarEntry/get');
}
export const createAppointment = (appointment) =>{
    return put('calendarEntry/create',appointment);
} 