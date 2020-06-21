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
    height: 300,
    backgroundColor: "white",
    justifyContent: "center",
    borderRadius: 20,
  },
  modalButton: {
    backgroundColor: "#66CDAA",
    borderRadius: 20,
    padding: 10,
    elevation: 2,
    margin: 5,
    width: 150,
    alignSelf: "center",
  },
  textStyle: {
    color: "white",
    fontWeight: "bold",
    textAlign: "center",
  },
  modalText: {
    marginBottom: 15,
    textAlign: "center",
  },
  textInput: {
    height: 40,
    width: 40,
    marginBottom: 20,
    alignSelf: "center",
    borderBottomColor: "grey",
    borderBottomWidth: 1,
  },
});

export default styles;
