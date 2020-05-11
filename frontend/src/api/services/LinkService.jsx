import { fetch, put, del } from "../apiService";

export const getAllLinks = () => {
  return fetch("link/get");
};
