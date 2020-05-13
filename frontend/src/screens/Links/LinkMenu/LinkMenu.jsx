import React, { useState } from "react";
import { View, TextInput } from "react-native";
import AppModal from "../../../components/AppModal/AppModal";
import AppButton from "../../../components/AppButton/AppButton";
import styles from "./LinkMenu.style";

const LinkMenu = ({ navigation, route }) => {
  const [link, setLink] = useState(route.params?.link);
  const [editMode, setEditMode] = useState(false);
  const [url, setUrl] = useState("");
  const [description, setDescription] = useState("");

  const onChangeLink = () => {
    setEditMode(!editMode);
  };

  const onDeleteLink = () => {};

  return (
    <View style={styles.container}>
      {editMode ? (
        <View>
          <AppModal header="Link bearbeiten" height={320}>
            <TextInput
              style={styles.textInput}
              value={link.url}
              onChangeText={(text) => setUrl(text)}
            />
            <TextInput
              style={styles.textInput}
              value={link.description}
              onChange={(text) => setDescription(text)}
            />
            <AppButton
              onPress={() => console.log("speichern ausgewählt")}
              text="Speichern"
            />
            <AppButton onPress={() => navigation.pop()} text="Abbrechen" />
          </AppModal>
        </View>
      ) : (
        <View>
          <AppModal header="Link: " description={link.url}>
            <AppButton onPress={() => onChangeLink()} text="Link bearbeiten" />
            <AppButton
              onPress={() => onDeleteLink()}
              text="Löschen"
              color="red"
            />
            <AppButton onPress={() => navigation.pop()} text="Abbrechen" />
          </AppModal>
        </View>
      )}
    </View>
  );
};

export default LinkMenu;
