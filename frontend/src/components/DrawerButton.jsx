import React from 'react'
import { FontAwesome } from "@expo/vector-icons";
import { DrawerActions, useNavigation } from "@react-navigation/native";

const DrawerButton = () => {
    const navigation = useNavigation();
    return (
        <FontAwesome.Button name="bars"
        iconStyle={{ marginLeft: 10 }}
        color="black"
        backgroundColor="#ffff"
        onPress={() => navigation.dispatch(DrawerActions.toggleDrawer())}
        />
    )
}

export default DrawerButton;