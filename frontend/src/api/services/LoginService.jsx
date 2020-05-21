import { post } from "../authService";

export const login = (username, password) => {
  return post("login", username, password);
};
