import React from "react";
import { View } from "react-native";
import {
  FlatList,
  TouchableHighlight,
  TouchableOpacity,
} from "react-native-gesture-handler";
import styles from "./Card.style";

const Card = (props) => {
  return (
    <View style={styles.container}>
        <View style={styles.item}>{props.children}</View>
    </View>
  );
};

export default Card;
