import { fetch } from "../apiService";

export const getAllNews = () => {
  return fetch("newsletter/get");
};
export const pingNewsletter = () => {
  return fetch("newsletter/ping");
};
