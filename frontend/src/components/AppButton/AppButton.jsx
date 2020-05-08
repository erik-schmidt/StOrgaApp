import React from "react";
import styles from "./AppButton.style";
import { TouchableHighlight, Text } from "react-native";

const AppButton = (props) => {
  const color = () => {
    if (props.color == "red") {
      return { ...styles.modalButton, backgroundColor: "#f00" };
    }
    return styles.modalButton;
  };

  return (
    <TouchableHighlight style={color()} onPress={props.onPress}>
      <Text style={styles.textStyle}>{props.text}</Text>
    </TouchableHighlight>
  );
};

export default AppButton;
