import React from "react";
import { TouchableHighlight, Text, View } from "react-native";
import styles from "./BottomButton.style";

const BottomButton = (props) => {
  return (
    <View style={styles.viewContainer}>
      <TouchableHighlight style={styles.openButton} onPress={props.onPress}>
        <Text style={styles.textStyle}>{props.text}</Text>
      </TouchableHighlight>
    </View>
  );
};

export default BottomButton;
