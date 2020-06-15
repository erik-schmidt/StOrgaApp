import Axios from "axios";
const axios = Axios.create({
  baseURL: "http://storga.hs-heilbronn.de:8888/auth/",
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
