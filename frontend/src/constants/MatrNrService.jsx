import { AsyncStorage } from 'react-native';

export const getMatrNr = async () => {
    const matrNr = await AsyncStorage.getItem("matrNr");
    return matrNr;
};
