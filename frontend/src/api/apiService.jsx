import Axios from "axios";
import { AsyncStorage } from "react-native";
const axios = Axios.create({
  baseURL: "http://storga.hs-heilbronn.de:8080/api/",
  responseType: "application/json",
});

axios.interceptors.request.use(
  async (config) => {
    const userToken = await AsyncStorage.getItem("token");
    config.headers["Authorization"] = `Bearer ${userToken}`;
    return config;
  },
  (err) => {
    return Promise.reject(err);
  }
);

//Objekt anfragen
export async function fetch(apiPath, param = "") {
  return await axios
    .get(apiPath, param)
    .then((res) => {
      return res;
    })
    .catch((error) => {
      return error.response;
    });
}

// Objekt übergeben
export async function post(apiPath, param = "") {
  return axios
    .post(apiPath, param)
    .then((res) => {
      return res;
    })
    .catch((error) => {
      return error.response;
    });
}

//Objekt updaten (bearbeiten)
export async function put(apiPath, param = "") {
  return axios
    .put(apiPath, param)
    .then((res) => {
      return res;
    })
    .catch((error) => {
      return error.response;
    });
}

// Objekt löschen
// matrikelnummer als parameter
export async function del(apiPath, param = "") {
  return axios
    .delete(apiPath, param)
    .then((res) => {
      return res;
    })
    .catch((error) => {
      return error.response;
    });
}
