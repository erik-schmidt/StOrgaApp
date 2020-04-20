import { StyleSheet } from "react-native";

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
  },
  textInput: {
    borderWidth: 1,
    padding: 3,
    margin: 3,
    borderRadius: 7,
    width: Math.round(Dimension.get('window').width)-50,
    
  },
});

export default styles;