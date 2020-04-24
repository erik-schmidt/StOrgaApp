import { fetch, put, del } from "../apiService";

export const getAllCourses = () => {
  return fetch("course/get");
};

export const createCourse = (course) => {
  return put("course/create", course);
};

export const deleteCourse = (id) => {
  return del("course/delete/" + id);
};
