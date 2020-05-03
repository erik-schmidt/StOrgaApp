import React from "react";
import { View, TouchableOpacity } from "react-native";
import styles from "./Card.style";

const Card = (props) => {
  return (
    <TouchableOpacity onLongPress={props.onLongPress} onPress={props.onPress}>
      <View style={styles.container}>
        <View style={styles.item}>{props.children}</View>
      </View>
    </TouchableOpacity>
  );
};

export default Card;
