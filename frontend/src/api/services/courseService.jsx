import { fetch, put } from '../apiService';

export const getAllCourses = () => {
    return fetch("course/get");
}

export const createCourse = (course) => {
    return put("course/create", course);
}