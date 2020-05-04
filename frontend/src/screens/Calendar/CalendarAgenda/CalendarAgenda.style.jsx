import { StyleSheet } from "react-native";

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  item: {
    backgroundColor: "white",
    flex: 1,
    borderRadius: 5,
    padding: 10,
    marginRight: 10,
    marginTop: 17,
  },
  emptyDate: {
    backgroundColor: "white",
    alignItems: "center",
    height: 15,
    flex: 1,
    paddingTop: 30,
  },
  time: {
    backgroundColor: "white",
    flex: 1,
    marginRight: 10,
    fontSize: 25,
    color: "steelblue",
  },
  name: {
    backgroundColor: "white",
    flex: 1,
    marginRight: 10,
    marginTop: 17,
    fontSize: 20,
    color: "dimgrey",
  },
  info: {
    backgroundColor: "white",
    flex: 1,
    marginRight: 10,
    marginTop: 17,
    color: "slategrey",
  },
});

export default styles;
