import { post } from "../authService";

export const register = (student) => {
  return post("register", student);
};
