import React from "react";
import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";
import { Login } from "./screens/Login/Login";
import WebSocket from "./screens/Notifications/WebSocket";
import { Notifications2 } from "./screens/Notifications/Notifications2";
import { Notifications } from "./screens/Notifications/Notifications";

const Stack = createStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Login" component={Login} />
        <Stack.Screen name="Dashboard" component={Notifications2} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
