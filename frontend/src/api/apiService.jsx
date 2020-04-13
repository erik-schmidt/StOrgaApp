import Axios from "axios";
import * as HttpStatus from "http-status-codes";
const axios = Axios.create({
  baseURL: "http://localhost:8080/api/",
  responseType: "application/json",
});

//Objekt anfragen
export async function fetch(apiPath, param = "") {
  return axios
    .get(apiPath, param)
    .then((res) => {
      if (res !== undefined && res.status === HttpStatus.OK) {
        return res;
      }
      return undefined;
    })
    .catch((error) => {
      if (error.response !== undefined) {
        console.log(
          `An Error occured doing a REST-Request ${error.response.status}`
        );
      }
      return undefined;
    });
}

// Objekt übergeben 
export async function post(apiPath, param = "") {
  return axios
    .post(apiPath, param)
    .then((res) => {
      if (res !== undefined && res.status === HttpStatus.OK) {
        return res;
      }
      return undefined;
    })
    .catch((error) => {
      if (error.response !== undefined) {
        console.log(
          `An Error occured doing a REST-Request ${error.response.status}`
        );
      }
      return undefined;
    });
}

//Objekt updaten (bearbeiten)
export async function put(apiPath, param = "") {
  return axios
    .put(apiPath, param)
    .then((res) => {
      if (res !== undefined && res.status === HttpStatus.OK) {
        return res;
      }
      return undefined;
    })
    .catch((error) => {
      if (error.response !== undefined) {
        console.log(
          `An Error occured doing a REST-Request ${error.response.status}`
        );
      }
      return undefined;
    });
}

// Objekt löschen 
// matrikelnummer als parameter
export async function del(apiPath, param = "") {
  return axios
    .delete(apiPath, param)
    .then((res) => {
      if (res !== undefined && res.status === HttpStatus.OK) {
        return res.data;
      }
      return undefined;
    })
    .catch((error) => {
      if (error.response !== undefined) {
        console.log(
          `An Error occured doing a REST-Request ${error.response.status}`
        );
      }
      return undefined;
    });
}
