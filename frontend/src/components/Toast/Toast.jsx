import React from "react";
import { Modal, View, Text } from "react-native";
import styles from "./Toast.style";

const Toast = (props) => {
  const color = () => {
    if (props.color === "red") {
      return { ...styles.modalView, borderLeftColor: "red" };
    } else if (props.color === "green") {
      return { ...styles.modalView, borderLeftColor: "green" };
    } else {
      return styles.modalView;
    }
  };

  return (
    <Modal animationType="slide" transparent={true} visible={props.showModal}>
      <View style={styles.container}>
        <View style={color()}>
          <Text style={styles.textStyle}>{props.text}</Text>
        </View>
      </View>
    </Modal>
  );
};

export default Toast;
