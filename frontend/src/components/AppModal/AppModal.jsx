import React from "react";
import { View, Text } from "react-native";
import styles from "./AppModal.style";

const AppModal = (props) => {
  const style = () => {
    if (props.height && props.width) {
      return { ...styles.modalView, height: props.height, width: props.width };
    } else if (props.height) {
      return { ...styles.modalView, height: props.height };
    } else if (props.width) {
      return { ...styles.modalView, width: props.width };
    } else {
      return styles.modalView;
    }
  };

  return (
    <View style={style()}>
      <Text style={styles.modalHeader}>{props.header}</Text>
      <Text style={styles.modalText}>{props.description}</Text>
      {props.children}
    </View>
  );
};

export default AppModal;
