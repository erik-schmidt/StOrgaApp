import React from "react";
import { NavigationContainer } from "@react-navigation/native";
import DrawerNavigation from "./src/navigation";
import { AsyncStorage } from "react-native";
import { createStackNavigator } from "@react-navigation/stack";
import SplashScreen from "./src/screens/SplashScreen";

const App = ({ navigation }) => {
  const AuthContext = React.createContext();
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
        //TODO: Show toast that restoring token failed
      }
      //TODO: Verification to backend if token is valid!
      dispatch({ type: "RESTORE_TOKEN", token: userToken });
    };

    bootstrapAsync();
  }, []);

  const authContext = React.useMemo(
    () => ({
      signIn: async (data) => {
        //TODO: Send userdata
        //TODO: Get token from backend
        //TODO: Handle errors if sign in failed
        //TODO: Persist the token using `AsyncStorage`
        dispatch({ type: "SIGN_IN", token: "HERE SHOULD BE THE TOKEN!" });
      },
      signOut: () => dispatch({ type: "SIGN_OUT" }),
      signUp: async (data) => {
        //TODO: Send userdata to server
        //TODO: Get token from backend
        //TODO: Handle errors if sign up failed
        //TODO: persist the token using `AsyncStorage`
        dispatch({ type: "SIGN_IN", token: "HERE SHOULD BE THE TOKEN" });
      },
    }),
    []
  );

  return (
    <AuthContext.Provider value={authContext}>
      <NavigationContainer>
        <Stack.Navigator>
          {state.isLoading ? (
            <Stack.Screen name="Splash" component={SplashScreen} />
          ) : state.userToken == null ? (
            <Stack.Screen
              name="SignIn"
              component={SignInScreen}
              options={{
                title: "Sign in",
                animationTypeForReplace: state.isSignout ? "pop" : "push",
              }}
            />
          ) : (
            <DrawerNavigation />
          )}
        </Stack.Navigator>
      </NavigationContainer>
    </AuthContext.Provider>
  );
};

export default App;
