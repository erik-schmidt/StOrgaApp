import React from "react";
import { View, TouchableOpacity } from "react-native";
import styles from "./Card.style";

const Card = (props) => {
  return (
    <TouchableOpacity onLongPress={props.onLongPress} onPress={props.onPress}>
      <View style={styles.container}>
        <View
          style={
            props.modal === true
              ? { ...styles.item, backgroundColor: "rgba(0,0,0,0)" }
              : styles.item
          }
        >
          {props.children}
        </View>
      </View>
    </TouchableOpacity>
  );
};

export default Card;
