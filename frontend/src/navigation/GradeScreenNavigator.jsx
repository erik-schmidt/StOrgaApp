import React from 'react';
import { createStackNavigator, TransitionPresets } from '@react-navigation/stack';

const GradeScreenNavigator = ({ navigation }) => {
    const GradeStack = createStackNavigator();
    return (
        <GradeStack.Navigator
        mode="modal"
        screenOptions={() => ({
            gestureEnabled: true,
            cardOverlayEnabled: true,
            ...TransitionPresets.ModalPresentationIOS,
        })}
        >
            
        </GradeStack.Navigator>
    )
}