import React, { useState } from "react";
import { View, TextInput } from "react-native";
import AppModal from "../../../components/AppModal/AppModal";
import AppButton from "../../../components/AppButton/AppButton";
import styles from "./LinkMenu.style";
import { deleteLink, editLink } from "../../../api/services/LinkService";
import * as HttpStatus from "http-status-codes";

const LinkMenu = ({ navigation, route }) => {
  const [editMode, setEditMode] = useState(false);
  const [link, setLink] = useState(route.params?.link);
  const [url, setUrl] = useState("");
  const [description, setDescription] = useState("");

  const onChangeLink = () => {
    editLink(link.id, {
      link: url,
      linkDescription: description,
    })
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          navigation.navigate("LinkScreen", { linkEdited: true });
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
  };

  const onDeleteLink = () => {
    deleteLink(link.id)
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          navigation.navigate("LinkScreen", { linkDeleted: true });
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
      {editMode ? (
        <View>
          <AppModal header="Link bearbeiten" height={320}>
            <TextInput
              style={styles.textInput}
              onChangeText={(text) => setUrl(text)}
              value={url}
              placeholder={link.link}
            />
            <TextInput
              style={styles.textInput}
              value={description}
              onChangeText={(text) => setDescription(text)}
              placeholder={link.linkDescription}
            />
            <AppButton onPress={() => onChangeLink()} text="Speichern" />
            <AppButton onPress={() => navigation.pop()} text="Abbrechen" />
          </AppModal>
        </View>
      ) : (
        <View>
          <AppModal header="Link: " description={link.link}>
            <AppButton
              onPress={() => setEditMode(!editMode)}
              text="Link bearbeiten"
            />
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
