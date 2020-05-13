import { StyleSheet } from "react-native";

const styles = StyleSheet.create({
  container: {
    flex: 1,
    margin: 10,
    padding: 10,
    //flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
    alignSelf: "center",
  },
  textInput: {
    width: 200,
    height: 30,
    borderWidth: 1,
    padding: 1,
    marginTop: 10,
    marginBottom: 10,
    fontSize: 15,
    alignSelf: "center",
  },
  description: {
    width: 200,
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
