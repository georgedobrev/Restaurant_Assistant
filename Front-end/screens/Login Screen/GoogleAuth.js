import React from "react";
import { TouchableOpacity, Image, Text } from "react-native";
import * as Google from "expo-auth-session/providers/google";

const GoogleAuth = ({ request, onSignInPressed }) => {
  return (
    <TouchableOpacity
      style={{
        backgroundColor: "white",
        borderRadius: 5,
        paddingHorizontal: 6,
        paddingVertical: 6,
        flexDirection: "row",
        justifyContent: "center",
        alignItems: "middle",
      }}
      disabled={!request}
      onPress={() => {
        promptAsync().then((result) => {
          if (result.type === "success") {
            onSignInPressed(); // Call the onSignInPressed function on successful sign-in
          }
        });
      }}
    >
      <Image
        style={{ width: 24, height: 24 }}
        source={{
          uri: "https://i.ibb.co/j82DCcR/search.png",
        }}
      />
      <Text style={{ marginLeft: 4, fontSize: 20, fontWeight: "semibold" }}>
        Sign in with Google
      </Text>
    </TouchableOpacity>
  );
};

export default GoogleAuth;
