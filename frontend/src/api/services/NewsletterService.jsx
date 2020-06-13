import { fetch } from "../apiService";

export const getAllNews = () => {
  return fetch("news/get");
};
export const pingNewsletter = () => {
  return fetch("news/ping");
};
