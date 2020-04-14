import { StyleSheet } from "react-native";

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
  },
  centeredView: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    marginTop: 22,
  },
  headerContainer: {
    flex: 1,
    flexDirection: "row",
  },
  headerText: {
    fontSize: 15,
    fontWeight: "bold",
    margin: 10
  }
});

export default styles;
