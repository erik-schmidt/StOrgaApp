import { fetch, put, del } from "../apiService";

export const getAllCourses = () => {
  return fetch("interface/course/get");
};

export const createCourse = (course) => {
  return put("interface/course/create", course);
};

export const deleteCourse = (id) => {
  return del("interface/course/delete/" + id);
};
