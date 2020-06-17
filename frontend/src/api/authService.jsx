import Axios from "axios";
import * as HttpStatus from "http-status-codes";
const axios = Axios.create({
  baseURL: "http://192.168.0.213:8888/auth/",
  responseType: "application/json",
});

export async function post(apiPath, param = "") {
  return axios
    .post(apiPath, param)
    .then((res) => {
      return res;
    })
    .catch((error) => {
      if (error.response !== undefined) {
        console.log(
          `An Error occured doing a REST-Request ${error.response.status}`
        );
      }
      return error.response;
    });
}
