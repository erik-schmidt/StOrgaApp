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
    width: 250,
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
});

export default styles;
