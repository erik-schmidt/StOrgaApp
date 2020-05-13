import { StyleSheet } from "react-native";

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: "column",
    alignItems: "stretch",
    justifyContent: "center",
    alignSelf: "center",
  },
  modalView: {
    width: 300,
    height: 250,
    backgroundColor: "white",
    justifyContent: "center",
    borderRadius: 20,
  },
  modalButton: {
    backgroundColor: "#2196F3",
    borderRadius: 20,
    padding: 10,
    elevation: 2,
    margin: 10,
    width: 150,
    alignSelf: "center",
  },
  textStyle: {
    color: "white",
    fontWeight: "bold",
    textAlign: "center",
  },
  headerText: {
    fontWeight: "bold",
    alignSelf: "center",
    fontSize: 18,
    marginBottom: 15,
  },
  textInput: {
    height: 40,
    width: 220,
    marginBottom: 20,
    alignSelf: "center",
    borderBottomColor: "grey",
    borderBottomWidth: 1,
  },
});

export default styles;
