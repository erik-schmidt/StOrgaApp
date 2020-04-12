import React, { useState, useEffect } from "react";
import styles from "./InputModal.style";
import { Modal, View, Text, TouchableHighlight } from "react-native";
import { TextInput } from "react-native-gesture-handler";
import { FontAwesome } from "@expo/vector-icons";

const InputModal = (props) => {
  const { closeModal } = props;
  const [text, setText] = useState("");

  const saveContent = () => {
    props.saveContent(text);
    closeModal;
  };

  return (
    <View style={styles.centeredView}>
      <Modal
        animationType="slide"
        transparent={false}
        visible={true}
        onRequestClose={closeModal}
      >
        <View style={styles.headerView}>
          <FontAwesome.Button
            name="arrow-left"
            iconStyle={{ margin: 10 }}
            color="black"
            backgroundColor="#ffff"
            onPress={closeModal}
          />
          <Text style={styles.modalText}>{props.heading}</Text>
        </View>
        <View style={styles.centeredView}>
          <View style={styles.modalView}>
            <TextInput
              style={styles.textInput}
              placeholder={props.placeholder}
              onChangeText={(text) => setText(text)}
              defaultValue={text}
            />
            <View style={styles.buttonContainer}>
              <TouchableHighlight
                style={{ ...styles.openButton, backgroundColor: "#2196F3" }}
                onPress={saveContent}
              >
                <Text style={styles.textStyle}>Speichern</Text>
              </TouchableHighlight>
            </View>
          </View>
        </View>
      </Modal>
    </View>
  );
};

export default InputModal;
