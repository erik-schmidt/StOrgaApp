import React from 'react';
import {fetch} from '../apiService';

export const getAppointments = (param) => {
    return fetch('/date/ping');
}
