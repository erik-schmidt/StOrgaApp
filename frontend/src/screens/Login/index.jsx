import React, { useState } from "react";
import { View, TextInput, Text, TouchableHighlight, Alert } from "react-native";
import styles from "./index.style";
import AuthConext from "../../constants/AuthContext";
import Toast from "../../components/Toast/Toast";

const LoginScreen = ({ navigation }) => {
  const { signIn } = React.useContext(AuthConext);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [visible, setVisible] = useState(false);

  return (
    <View style={styles.container}>
      <Text style={styles.headerText}>Melde dich bei StOrga an</Text>
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
        secureTextEntry
        style={styles.textInput}
      />
      <View style={styles.signUpContainer}>
        <Text style={styles.newToText}>Neu bei StOrga?</Text>
        <Text
          style={styles.signUpText}
          onPress={() => navigation.navigate("RegisterScreen")}
        >
          Registrieren
        </Text>
      </View>
      <TouchableHighlight
        style={styles.button}
        onPress={() => {
          signIn({ username, password });
        }}
      >
        <Text style={styles.textStyle}>Login</Text>
      </TouchableHighlight>
      <Toast color="red" showModal={visible} text={errorMessage} />
    </View>
  );
};

export default LoginScreen;
