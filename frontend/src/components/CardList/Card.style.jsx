import { StyleSheet } from "react-native";
import Constants from "expo-constants";

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: 10,
    marginBottom: 10
  },
  item: {
      backgroundColor: "#FFFF",
      padding: 20,
      marginHorizontal: 16,
      shadowOffset: { width: 1, height: 1},
      shadowColor: '#333',
      shadowOpacity: 0.3,
      shadowRadius: 2,
      elevation: 3,
  },
});

export default styles;
