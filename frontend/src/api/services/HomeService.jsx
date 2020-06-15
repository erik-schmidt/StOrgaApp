import React from "react";
import { fetch } from "../apiService";
import { AsyncStorage } from "react-native";

export const getHomescreenItems = async () => {
  const matrNr = await AsyncStorage.getItem("matrNr");
  return fetch(`homescreen/getHomescreen/${matrNr}`);
};
