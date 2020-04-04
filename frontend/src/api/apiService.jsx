import axios from "axios";
import * as HttpStatus from "http-status-codes";
const config = require("../../config");
const axios = axios.create({
  baseURL: config.api.baseURL,
  responseType: "json",
});

export async function get(apiPath, param = "") {
  return await axios
    .get(apiPath, param)
    .then((response) => {
      if (response.status === HttpStatus.OK) {
        return response.data;
      } else {
        return undefined;
      }
    })
    .catch((error) => {
      console.log(
        `An Error occured doing a REST-Request ${error.response.status}`
      );
      return undefined;
    });
}

export async function post(apiPath, value) {
  return await axios
    .post(apiPath, value)
    .then((response) => {
      if (response.status === HttpStatus.OK) {
        return response.data;
      } else {
        return undefined;
      }
    })
    .catch((error) => {
      console.log(
        `An Error occured doing a REST-Request ${error.response.status}`
      );
      return undefined;
    });
}

export async function put(apiPath, value) {
  return axios
    .put(apiPath, value)
    .then((response) => {
      if (response.status === HttpStatus.OK) {
        return response.data;
      } else {
        return undefined;
      }
    })
    .catch((error) => {
      console.log(
        `An Error occured doing a REST-Request ${error.response.status}`
      );
      return undefined;
    });
}

export async function del(apiPath, value) {
  return axios
    .delete(apiPath, value)
    .then((response) => {
      if (response.status === HttpStatus.OK) {
        return response.data;
      } else {
        return undefined;
      }
    })
    .catch((error) => {
      console.log(
        `An Error occured doing a REST-Request ${error.response.status}`
      );
      return undefined;
    });
}
