import React, { useEffect } from "react";
import {
  View,
  Text,
  Image,
  TextInput,
  TouchableOpacity,
  ImageBackground,
} from "react-native";
import * as WebBrowser from "expo-web-browser";
import * as Google from "expo-auth-session/providers/google";
import { useNavigation } from "@react-navigation/native";
import ImageSection from "./ImageSection";

WebBrowser.maybeCompleteAuthSession();

export function LoginScreen({}) {
  const [accessToken, setAccessToken] = React.useState(null);
  const [user, setUser] = React.useState(null);

  const [request, response, promptAsync] = Google.useIdTokenAuthRequest({
    clientId:
      "204367786739-m0li9b5tkdqt0j44b0jh17et4ukpgme7.apps.googleusercontent.com",
    iosCliendId:
      "204367786739-m82dvi0rqag8943ja9676l5ocjemkup4.apps.googleusercontent.com",
    androidClientId:
      "204367786739-s9v0h7p0vga6lm81rkic6hs3a2qeokak.apps.googleusercontent.com",
  });

  const navigation = useNavigation();

  useEffect(() => {
    if (response?.type === "success" && response.authentication) {
      setAccessToken(response.authentication.accessToken);
      accessToken && fetchUserInfo();
    }
  }, [response, accessToken]);

  async function fetchUserInfo() {
    if (accessToken) {
      const response = await fetch(
        "https://www.googleapis.com/userinfo/v2/me",
        {
          headers: { Authorization: `Bearer ${accessToken}` },
        }
      );
      const userInfo = await response.json();
      setUser(userInfo);
    }
  }

  const ShowUserInfo = () => {
    if (user) {
      return (
        <View>
          <Text>Welcome</Text>
          <Text>{user.name}</Text>
        </View>
      );
    }
  };

  const onSignInPressed = () => {
    navigation.navigate("Dashboard");
  };

  return (
    <View style={{ flex: 1, backgroundColor: "#615149" }}>
      <ImageSection />
      <View
        style={{
          flex: 1,
          backgroundColor: "white",
          paddingHorizontal: 30,
          borderTopLeftRadius: 40,
          borderTopRightRadius: 40,
        }}
      >
        <View style={{ marginBottom: 20, marginTop: 30 }}>
          <Text style={{ color: "#333", marginLeft: 4 }}>Email address</Text>
          <TextInput
            style={{
              padding: 4,
              backgroundColor: "#F6F1E9",
              color: "#333",
              borderRadius: 10,
              marginBottom: 3,
              marginTop: 5,
              padding: 12,
            }}
            placeholder="Enter email"
          />

          <Text style={{ color: "#333", marginLeft: 4, marginTop: 10 }}>
            Password
          </Text>
          <TextInput
            style={{
              padding: 4,
              backgroundColor: "#F6F1E9",
              color: "#333",
              borderRadius: 10,
              marginBottom: 3,
              marginTop: 5,
              padding: 12,
            }}
            placeholder="Enter email"
            secureTextEntry
          />

          <TouchableOpacity
            style={{
              flexDirection: "row",
              justifyContent: "flex-end",
              marginBottom: 5,
            }}
          >
            <Text>Forgot password?</Text>
          </TouchableOpacity>

          <TouchableOpacity
            style={{
              padding: 10,
              backgroundColor: "#ecd282",
              borderRadius: 20,
              marginTop: 10,
              shadowColor: "black",
              shadowOffset: { width: 0, height: 2 },
              shadowOpacity: 0.3,
              shadowRadius: 2,
              elevation: 3,
            }}
            onPress={onSignInPressed}
          >
            <Text
              style={{
                fontSize: 16,
                fontWeight: "bold",
                color: "#000",
                textAlign: "center",
              }}
            >
              Login
            </Text>
          </TouchableOpacity>

          <Text
            style={{
              textAlign: "center",
              paddingTop: 5,
              fontWeight: "bold",
              fontSize: 20,
              marginVertical: 20,
            }}
          >
            Or
          </Text>

          <View>
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
              <Text
                style={{ marginLeft: 4, fontSize: 20, fontWeight: "semibold" }}
              >
                Sign in with Google
              </Text>
            </TouchableOpacity>
          </View>
        </View>
      </View>
    </View>
  );
}
