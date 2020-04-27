import { StyleSheet } from "react-native";

const styles = StyleSheet.create({
  modalView: {
    margin: 25,
    borderRadius: 5,
    height: 1,
    justifyContent: "center",
    backgroundColor: "white",
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
  container: {
    position: "absolute",
    bottom: 0,
    alignSelf: "center",
  },
  textStyle: {
    textAlign: "center",
  },
});

export default styles;
