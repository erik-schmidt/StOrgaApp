import { StyleSheet, Dimensions } from "react-native";

const styles = StyleSheet.create({
  container: {
    width: Dimensions.get("window").width,
    alignItems: "center",
    justifyContent: "center",
    alignSelf: "center",
  },
  textInput: {
    width: 200,
    height: 40,
    borderWidth: 1,
    padding: 1,
    marginTop: 10,
    marginBottom: 10,
    fontSize: 15,
    alignSelf: "center",
  },
  description: {
    width: 200,
    marginTop: 10,
    backgroundColor: "white",
    fontSize: 20,
    color: "steelblue",
    alignSelf: "center",
  },
  picker: {
    width: 200,
    marginTop: 10,
    marginBottom: 10,
    alignSelf: "center",
  },
});

export default styles;
