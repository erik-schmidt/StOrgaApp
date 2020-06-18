import { StyleSheet } from "react-native";

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignSelf: "center",
    flexDirection: "column",
    alignItems: "stretch",
  },
  cardText: {
    flexDirection: "row",
    flex: 1,
  },
  courseHeader: {
    fontWeight: "bold",
    fontSize: 20,
  },
  courseDescription: {
    fontSize: 20,
    marginBottom: 5,
  },
  centeredView: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    marginTop: 22,
  },
  modalView: {
    margin: 25,
    borderRadius: 5,
    height: 1,
    justifyContent: "center",
    backgroundColor: "white",
    borderLeftColor: "red",
    borderWidth: 0.5,
    borderLeftWidth: 10,
    padding: 29,
    alignItems: "center",
    shadowColor: "#000",
    shadowOffset: {
      width: 0,
      height: 12,
    },
    shadowOpacity: 0.58,
    shadowRadius: 16,
    elevation: 30,
  },
  modalButton: {
    backgroundColor: "#2196F3",
    borderRadius: 20,
    padding: 10,
    elevation: 2,
    margin: 5,
    width: 150,
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
