import { StyleSheet } from "react-native";

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
  },
  button: {
    position: "relative",
    backgroundColor: "#2196F3",
    borderRadius: 20,
    padding: 10,
    elevation: 2,
    marginTop: 25,
    width: "90%",
    alignSelf: "center",
  },
  bottom: {
    flex: 1,
    justifyContent: "flex-end",
    marginBottom: 35,
  },
  textStyle: {
    color: "white",
    fontWeight: "bold",
    textAlign: "center",
  },
  signUpText: {
    marginLeft: 5,
    color: "#006EFF",
    fontWeight: "bold",
    fontSize: 16,
  },
  newToText: {
    fontSize: 16,
  },
  headerText: {
    fontWeight: "bold",
    fontSize: 22,
    marginLeft: 25,
    marginBottom: 25,
  },
  signUpContainer: {
    justifyContent: "flex-start",
    flexDirection: "row",
    marginLeft: 25,
  },
  textInput: {
    fontSize: 16,
    margin: 25,
    height: 20,
    width: "80%",
    borderBottomColor: "grey",
    borderBottomWidth: 0.6,
  },
});

export default styles;
