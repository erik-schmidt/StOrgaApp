import { StyleSheet } from "react-native";

const styles = StyleSheet.create({
  container: {
    flex: 1,
    margin: 10,
    padding:10,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: 'white',
  },
  textInput: {
    width:200,
    height:30,
    borderWidth: 1,
    padding: 1,
    marginTop:10,
    marginBottom:10,
    fontSize: 15
    //width: Math.round(Dimension.get('window').width)-50,
  },
  description: {
    width:200,
    backgroundColor: 'white',
    fontSize: 20,
    color: 'steelblue',
  },
  picker:{
    width:200,
    marginTop:10,
    marginBottom:10,
  }
});

export default styles;