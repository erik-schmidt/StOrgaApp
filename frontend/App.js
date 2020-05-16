import React from "react";
import { NavigationContainer } from "@react-navigation/native";
import DrawerNavigation from "./src/navigation";
import { AsyncStorage } from "react-native";
import { createStackNavigator } from "@react-navigation/stack";
import SplashScreen from "./src/screens/SplashScreen";
import RegisterScreen from "./src/screens/Register";
import LoginScreen from "./src/screens/Login";
import { AuthProvider } from "./src/constants/AuthContext";
import { login } from "./src/api/services/LoginService";
import { register } from "./src/api/services/RegisterService";

const App = ({ navigation }) => {

  const Stack = createStackNavigator();

  const [state, dispatch] = React.useReducer(
    (prevState, action) => {
      switch (action.type) {
        case "RESTORE_TOKEN":
          return {
            ...prevState,
            userToken: action.token,
            isLoading: false,
          };
        case "SIGN_UP": 
          return {
            ...prevState,
            isSignout: false,
            userToken: action.token,
        };
        case "SIGN_IN":
          return {
            ...prevState,
            isSignout: false,
            userToken: action.token,
          };
        case "SIGN_OUT":
          return {
            ...prevState,
            isSignout: true,
            userToken: null,
          };
      }
    },
    {
      isLoading: true,
      isSignout: false,
      userToken: null,
    }
  );

  React.useEffect(() => {
    const bootstrapAsync = async () => {
      let userToken;

      try {
        userToken = await AsyncStorage.getItem("userToken");
      } catch (e) {
        Alert.alert("Wiederherstellung der Daten fehlgeschlagen", e);
      }
      dispatch({ type: "RESTORE_TOKEN", token: userToken });
    };

    bootstrapAsync();
  }, []);

  const _storeDate = async (itemString, item) => {
    try {
      await AsyncStorage.setItem(itemString, item);
    } catch {
      Alert.alert(
        "Einloggen",
        "Etwas ist während der Authentifizierung schief gelaufen, versuche es bitte erneut!"
      );
    }
  };

  const _retrieveData = async (itemString) => {
    try {
      const value = await AsyncStorage.getItem(itemString);
      if (value != null) {
        return value;
      }
    } catch {
      Alert.alert(
        "Fehlschlag",
        "Etwas ist während dem holen der Daten fehlgeschlagen"
      );
    }
  };

  const authContext = React.useMemo(
    () => ({
      signIn: async (data) => {
        let token;
        login(data)
          .then((res) => {
            console.log(res);
            if (res !== undefined) {
              _storeDate("token", res.data);
            } else {
              throw new Error();
            }
          })
          .catch((err) => {
            alert("Login ist fehlgeschlagen", err);
          });
        dispatch({ type: "SIGN_IN", token: _retrieveData('token') });
      },
      signOut: () => dispatch({ type: "SIGN_OUT" }),
      signUp: async (data) => {
        register(data)
          .then((res) => {
            console.log(res);
            if (res !== undefined) {
              _storeDate("Student", res.data);
            }
          })
          .catch((err) => {
            alert("Registrierung fehlgeschlagen", err);
          });
        dispatch({ type: "SIGN_UP", token: "TEST" });
      },
    }),
    []
  );

  return (
    <AuthProvider value={authContext}>
      <NavigationContainer>
        {state.isLoading ? (
          <Stack.Navigator>
            <Stack.Screen
              name="Splash"
              component={SplashScreen}
              options={{ animationEnabled: false, headerShown: false }}
            />
          </Stack.Navigator>
        ) : state.userToken == null ? (
          <Stack.Navigator>
            <Stack.Screen
              name="LoginScreen"
              component={LoginScreen}
              options={{
                title: "",
                animationTypeForReplace: state.isSignout ? "pop" : "push",
              }}
            />
            <Stack.Screen
              name="RegisterScreen"
              component={RegisterScreen}
              options={{
                title: "",
                animationTypeForReplace: state.isSignout ? "pop" : "push",
              }}
            />
          </Stack.Navigator>
        ) : (
          <DrawerNavigation />
        )}
      </NavigationContainer>
    </AuthProvider>
  );
};

export default App;
