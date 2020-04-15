import { fetch } from '../apiService';

export const getAllCourses = () => {
    return fetch("course/get");
}