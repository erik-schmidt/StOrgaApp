import React, { useState } from "react";
import { View, Button, Text } from "react-native";
import { TextInput } from "react-native-gesture-handler";
import styles from "./CreateCourseModal.style";

const CourseModal = ({ navigation }) => {
  const [text, setText] = useState("");

  return (
    <View style={styles.container}>
      <TextInput
        style={styles.textInput}
        placeholder="Kursname"
        onChangeText={(text) => setText(text)}
        defaultValue={text}
      />
      <TextInput style={styles.textInput} placeholder=""/>
      <Button onPress={() => navigation.navigate('Fächer', { courseName: text})} title="Speichern"/>
    </View>
  );
};

export default CourseModal;
