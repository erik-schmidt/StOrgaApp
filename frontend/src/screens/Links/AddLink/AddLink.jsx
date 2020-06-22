import React, { useState } from "react";
import { View } from "react-native";
import AppModal from "../../../components/AppModal/AppModal";
import { TextInput } from "react-native-gesture-handler";
import AppButton from "../../../components/AppButton/AppButton";
import styles from "./AddLink.style";
import { addLink } from "../../../api/services/LinkService";
import * as HttpStatus from "http-status-codes";

const AddLink = ({ navigation }) => {
  const [link, setLink] = useState("");
  const [linkDescription, setDescription] = useState("");

  const onSave = () => {
    addLink({ linkDescription, link })
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          navigation.navigate("LinkScreen");
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
  };

  return (
    <View style={styles.container}>
      <AppModal header="Link hinzufügen" height={380}>
        <TextInput
          style={styles.textInput}
          value={linkDescription}
          onChangeText={(text) => setDescription(text)}
          placeholder="Bsp.: 'Google Suchmaschine'"
        />
        <TextInput
          style={styles.textInput}
          value={link}
          onChangeText={(text) => setLink(text)}
          placeholder="Bsp.: 'http://www.google.de'"
        />
        <AppButton text="Speichern" onPress={() => onSave()} />
        <AppButton text="Abbrechen" onPress={() => navigation.pop()} />
      </AppModal>
    </View>
  );
};

export default AddLink;
