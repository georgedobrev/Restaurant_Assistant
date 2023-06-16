import React from "react";
import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";
import { Login } from "./screens/Login/Login";
import WebSocket from "./screens/Notifications/WebSocket";

const Stack = createStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Login" component={Login} />
        <Stack.Screen name="Dashboard" component={WebSocket} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
