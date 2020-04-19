import React from "react";
import { View } from "react-native";
import styles from "./Card.style";
import { TouchableOpacity } from "react-native-gesture-handler";

const Card = (props) => {
  return (
    <TouchableOpacity onPress={props.onPress}>
      <View style={styles.container}>
        <View style={styles.item}>{props.children}</View>
      </View>
    </TouchableOpacity>
  );
};

export default Card;
