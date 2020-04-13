import React from 'react';
import {fetch} from '../apiService';

export const pingStudent = () => {
    return fetch('/student/ping');
}
