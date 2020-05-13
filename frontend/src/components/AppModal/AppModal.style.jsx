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
    height: "80%",
    backgroundColor: "white",
    justifyContent: "center",
    borderRadius: 20,
  },
  modalHeader: {
    marginBottom: 15,
    textAlign: "center",
    marginBottom: 5,
    fontWeight: "bold",
    fontSize: 20,
  },
  modalText: {
    marginBottom: 15,
    textAlign: "center",
  },
});

export default styles;
