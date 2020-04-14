import React from "react";
import styles from "./SeperatedText.style";
import { View, Text } from 'react-native'

const SeperatedText = (props) => {
  return (
    <View style={styles.container}>
      <Text style={styles.textBold}>{props.title}</Text>
      <Text>{props.content}</Text>
    </View>
  );
};

export default SeperatedText;
