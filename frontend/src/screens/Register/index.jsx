import React, { useState } from "react";
import {
  Text,
  TextInput,
  TouchableHighlight,
  KeyboardAvoidingView,
  Platform,
} from "react-native";
import styles from "./index.style";
import AuthContext from "../../constants/AuthContext";
import { ScrollView } from "react-native-gesture-handler";

const RegisterScreen = ({ navigation }) => {
  const { signUp } = React.useContext(AuthContext);
  const [matrNr, setMatrNr] = useState("");
  const [studentPrename, setStudentPrename] = useState("");
  const [studentFamilyname, setStudentFamilyname] = useState("");
  const [fieldOfStudy, setFieldOfStudy] = useState("");
  const [currentSemester, setCurrentSemester] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  return (
    <ScrollView>
      <KeyboardAvoidingView
        enabled={true}
        behavior={Platform.OS === "ios" ? "height" : null}
        keyboardVerticalOffset={Platform.OS === "ios" ? 80 : 0}
        style={{ flex: 1, flexDirection: "column", justifyContent: "center" }}
      >
        <Text style={styles.headerText}>Registriere dich bei StOrga!</Text>
        <TextInput
          placeholder="Matrikelnummer"
          value={matrNr}
          onChangeText={(text) => setMatrNr(text)}
          style={styles.textInput}
        />
        <TextInput
          placeholder="Vorname"
          value={studentPrename}
          onChangeText={(text) => setStudentPrename(text)}
          style={styles.textInput}
        />
        <TextInput
          placeholder="Nachname"
          value={studentFamilyname}
          onChangeText={(text) => setStudentFamilyname(text)}
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
          secureTextEntry
          value={password}
          onChangeText={(text) => setPassword(text)}
          style={styles.textInput}
        />
        <TouchableHighlight
          onPress={() => {
            signUp({
              matrNr,
              studentPrename,
              studentFamilyname,
              fieldOfStudy,
              currentSemester,
              username,
              password,
            });
            navigation.pop();
          }}
          style={styles.button}
        >
          <Text style={styles.textStyle}>Registrieren</Text>
        </TouchableHighlight>
      </KeyboardAvoidingView>
    </ScrollView>
  );
};

export default RegisterScreen;
