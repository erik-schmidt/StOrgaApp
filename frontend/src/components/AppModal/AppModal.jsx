import React from "react";
import { View, Text } from "react-native";
import styles from "./AppModal.style";

const AppModal = (props) => {
  return (
    <View style={styles.modalView}>
      <Text style={styles.modalHeader}>{props.header}</Text>
      <Text style={styles.modalText}>{props.description}</Text>
      {props.children}
    </View>
  );
};

export default AppModal;
