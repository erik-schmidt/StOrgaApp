import { fetch, put, del } from '../apiService';
import { AsyncStorage } from 'react-native';
import { getMatrNr } from '../../constants/MatrNrService.jsx';

export const getGrades = async () => {
    return fetch(`grade/${getMatrNr}/get`);
}

export const addGrade = async (gradeCourse) => {
    return put(`grade/${getMatrNr}/add`, gradeCourse);
}

export const getAverage = async () => {
    return fetch(`grade/${getMatrNr}/getAverage`);
}

export const deleteGrade = async (number) => {
    return del(`grade/${getMatrNr}/delete/${number}`);
}
