import { fetch, put, del, post } from "../apiService";
import { AsyncStorage } from 'react-native'

export const getAllLinks = async () => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return fetch(`link/get/${matrNr}`);
};

export const addLink = async (link) => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return post(`link/add/${matrNr}`, link);
}
