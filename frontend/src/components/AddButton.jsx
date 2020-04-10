import React from "react";
import { FontAwesome } from "@expo/vector-icons";

const AddButton = (props) => {
  return (
    <FontAwesome.Button
      name="plus"
      iconStyle={{ marginRight: 10 }}
      color="black"
      backgroundColor="#ffff"
      onPress={props.onPress}
    />
  );
};

export default AddButton;
