import React, { useState } from "react";
import { View } from "react-native";
import AppModal from "../../../components/AppModal/AppModal";
import { TextInput } from "react-native-gesture-handler";
import AppButton from "../../../components/AppButton/AppButton";
import styles from "./AddLink.style";

const AddLink = ({ navigation }) => {
  const [link, setLink] = useState("");
  const [description, setDescription] = useState("");

  return (
    <View style={styles.container}>
      <AppModal header="Link hinzufügen" height={320}>
        <TextInput
          style={styles.textInput}
          value={link}
          onChangeText={(text) => setLink(text)}
          placeholder="Bsp.: 'http://www.google.de'"
        />
        <TextInput
          style={styles.textInput}
          value={description}
          onChangeText={(text) => setDescription(text)}
          placeholder="Bsp.: 'Google Suchmaschine'"
        />
        <AppButton
          text="Speichern"
          onPress={() => console.log("Speichern ausgewählt")}
        />
        <AppButton text="Abbrechen" onPress={() => navigation.pop()} />
      </AppModal>
    </View>
  );
};

export default AddLink;
