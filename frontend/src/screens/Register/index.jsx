import React, { useState } from "react";
import Student from "../../models/Student";
import {
  View,
  Text,
  TextInput,
  TouchableHighlight,
  ScrollView,
} from "react-native";
import styles from "./index.style";
import AuthContext from "../../constants/AuthContext";
import Toast from "../../components/Toast/Toast";

const RegisterScreen = ({ navigation }) => {
  const { signUp } = React.useContext(AuthContext);
  const [matrNr, setMatrNr] = useState("");
  const [prename, setPrename] = useState("");
  const [familyname, setFamilyname] = useState("");
  const [fieldOfStudy, setFieldOfStudy] = useState("");
  const [currentSemester, setCurrentSemester] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  return (
    <ScrollView style={styles.container}>
      <Text style={styles.headerText}>Registriere dich bei StOrga!</Text>
      <TextInput
        placeholder="Matrikelnummer"
        value={matrNr}
        onChangeText={(text) => setMatrNr(text)}
        style={styles.textInput}
      />
      <TextInput
        placeholder="Vorname"
        value={prename}
        onChangeText={(text) => setPrename(text)}
        style={styles.textInput}
      />
      <TextInput
        placeholder="Nachname"
        value={familyname}
        onChangeText={(text) => setFamilyname(text)}
        style={styles.textInput}
      />
      <TextInput
        placeholder="Studiengang"
        value={fieldOfStudy}
        onChangeText={(text) => setFieldOfStudy(text)}
        style={styles.textInput}
      />
      <TextInput
        placeholder="Aktuelles Semester"
        value={currentSemester}
        onChangeText={(text) => setCurrentSemester(text)}
        style={styles.textInput}
      />
      <TextInput
        placeholder="Nutzername"
        value={username}
        onChangeText={(text) => setUsername(text)}
        style={styles.textInput}
      />
      <TextInput
        placeholder="Passwort"
        value={password}
        onChangeText={(text) => setPassword(text)}
        style={styles.textInput}
      />
      <TouchableHighlight
        onPress={() => {
          signUp(
            new Student(
              matrNr,
              prename,
              familyname,
              fieldOfStudy,
              currentSemester,
              username,
              password
            )
          );
          navigation.pop();
        }}
        style={styles.button}
      >
        <Text style={styles.textStyle}>Registrieren</Text>
      </TouchableHighlight>
      <Toast color="red" showModal={showModal} text={errorMessage} />
    </ScrollView>
  );
};

export default RegisterScreen;
