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
import * as HttpStatus from "http-status-codes";

const App = () => {
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
        alert(e);
      }
      dispatch({ type: "RESTORE_TOKEN", token: userToken });
    };

    bootstrapAsync();
  }, []);

  const authContext = React.useMemo(
    () => ({
      signIn: async (data) => {
        login(data)
          .then(async (res) => {
            if (res.status === HttpStatus.OK) {
              await AsyncStorage.setItem("token", res.data.token);
              await AsyncStorage.setItem("matrNr", res.data.matrNr);
              const token = await AsyncStorage.getItem("token");
              dispatch({ type: "SIGN_IN", token: token });
            } else {
              throw new Error(res.data);
            }
          })
          .catch((err) => {
            alert("Login fehlgeschlagen " + err);
          });
      },
      signOut: () => dispatch({ type: "SIGN_OUT" }),
      signUp: async (data) => {
        register(data)
          .then((res) => {
            if (res.status === HttpStatus.OK) {
              alert(
                "Registration erfolgreich. Nach dem Login kannst du loslegen!"
              );
              dispatch({ type: "SIGN_UP", token: null });
            } else {
              throw new Error(res.data);
            }
          })
          .catch((err) => {
            alert("Registrierung fehlgeschlagen " + err);
          });
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
        ) : state.userToken === null ? (
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
