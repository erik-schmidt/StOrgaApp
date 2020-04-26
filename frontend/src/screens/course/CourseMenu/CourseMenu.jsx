import React, { useState } from "react";
import { View, Text, TouchableHighlight } from "react-native";
import styles from "./CourseMenu.style";

const CourseMenu = ({ navigation, route }) => {
  const [course, setCourse] = useState(route.params?.course);

  return (
    <View style={styles.container}>
      <View style={styles.modalView}>
        <Text style={{ ...styles.modalText, fontWeight: "bold", fontSize: 20 }}>
          Veranstaltung: {course.description}
        </Text>
        <TouchableHighlight
          style={styles.modalButton}
          onPress={() => console.log("Ändern wurde ausgewählt")}
        >
          <Text style={styles.textStyle}>Note ändern</Text>
        </TouchableHighlight>
        <TouchableHighlight
          style={{ ...styles.modalButton, backgroundColor: "#f00" }}
          onPress={() => console.log("Delete pressed")}
        >
          <Text style={styles.textStyle}>Löschen</Text>
        </TouchableHighlight>
        <TouchableHighlight
          style={{ ...styles.modalButton, marginTop: 25 }}
          onPress={() => navigation.pop()}
        >
          <Text style={styles.textStyle}>Abbrechen</Text>
        </TouchableHighlight>
      </View>
    </View>
  );
};

export default CourseMenu;
