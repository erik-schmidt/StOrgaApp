import { fetch, put, del, post } from "../apiService";
import { AsyncStorage } from 'react-native'

export const getAllLinks = async () => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return fetch(`link/${matrNr}/get`);
};

export const deleteLink = async (linkId) => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return del(`link/${matrNr}/delete/${linkId}`)
}

export const editLink = async (linkId, link) => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return put(`link/${matrNr}/put/${linkId}`, link);
}

export const addLink = async (link) => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return put(`link/${matrNr}/add`, link);
}
