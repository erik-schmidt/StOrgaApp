import React, { useState } from "react";
import { View, TextInput, Text, TouchableHighlight, Alert } from "react-native";
import AppButton from "../../components/AppButton/AppButton";
import styles from "./index.style";
import AuthConext from "../../constants/AuthContext";

const LoginScreen = ({ navigation }) => {
  const { signIn } = React.useContext(AuthConext);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  return (
    <View style={styles.container}>
      <Text style={styles.headerText}>Melde dich bei StOrga an</Text>
      <TextInput
        placeholder="Matrikelnummer"
        value={username}
        onChangeText={setUsername}
        style={styles.textInput}
      />
      <TextInput
        placeholder="Passwort"
        value={password}
        onChangeText={setPassword}
        secureTextEntry
        style={styles.textInput}
      />
      <View style={styles.signUpContainer}>
        <Text style={styles.newToText}>Neu bei StOrga?</Text>
        <Text style={styles.signUpText} onPress={() => Alert.alert("Pressed!")}>
          Registrieren
        </Text>
      </View>
      <TouchableHighlight
        style={styles.button}
        onPress={() => signIn({ username, password })}
      >
        <Text style={styles.textStyle}>Login</Text>
      </TouchableHighlight>
    </View>
  );
};

export default LoginScreen;
