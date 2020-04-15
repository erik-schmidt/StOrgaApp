import React, { useState } from "react";
import { View, Button, Text } from "react-native";
import { TextInput } from "react-native-gesture-handler";
import styles from "./CreateCalendarModal.style";

const CalendarModal = ({ navigation }) => {
  const [text, setText] = useState("");

  return (
    <View style={styles.container}>
      <TextInput
        style={styles.textInput}
        placeholder="Uhrzeit"
        onChangeText={(text) => setText(text)}
        defaultValue={text}
      />
      <TextInput style={styles.textInput} 
      placeholder="Bezeichnung"
      />
      <TextInput style={styles.textInput} 
      placeholder="Notizen"
      />
{/*     <Button onPress={() => navigation.navigate('Kalendar', { appointment: text})} title="Speichern"/> */}
    </View>
  );
};

export default CalendarModal;