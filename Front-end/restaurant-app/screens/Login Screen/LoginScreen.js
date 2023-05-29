import React, { useEffect } from "react";
import { View, Text, Image, TouchableOpacity } from "react-native";
import * as WebBrowser from "expo-web-browser";
import * as Google from "expo-auth-session/providers/google";
import { useNavigation } from "@react-navigation/native";
import ImageSection from "./ImageSection";
import BasicLogin from "./BasicLogin";

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
          <Text style={styles.welcomeText}>Welcome</Text>
          <Text style={styles.userNameText}>{user.name}</Text>
        </View>
      );
    }
  };

  const navigation = useNavigation();

  const onSignInPressed = () => {
    navigation.navigate("Dashboard");
  };


  return (
    <View style={styles.container}>
      <ImageSection />
      <View style={styles.contentContainer}>
      
        <BasicLogin/>

          <Text style={styles.orText}>Or</Text>

          <View>
            <TouchableOpacity
              style={styles.googleSignInContainer}
              disabled={!request}
              onPress={() => {
                promptAsync().then((result) => {
                  if (result.type === "success") {
                    onSignInPressed();
                  }
                });
              }}
            >
              <Image
                style={styles.googleSignInImage}
                source={{
                  uri: "https://i.ibb.co/j82DCcR/search.png",
                }}
              />
              <Text style={styles.googleSignInText}>
                Sign in with Google
              </Text>
            </TouchableOpacity>
          </View>
        </View>
      </View>
  );
}


const styles = {
  container: {
    flex: 1,
    backgroundColor: "#615149",
  },
  contentContainer: {
    flex: 1,
    backgroundColor: "white",
    paddingHorizontal: 30,
    borderTopLeftRadius: 40,
    borderTopRightRadius: 40,
  }, 
 
  orText: {
    textAlign: "center",
    paddingTop: 5,
    fontWeight: "bold",
    fontSize: 20,
    marginVertical: 10,
  },
  googleSignInContainer: {
    backgroundColor: "white",
    borderRadius: 5,
    paddingHorizontal: 6,
    paddingVertical: 6,
    flexDirection: "row",
    justifyContent: "center",
    alignItems: "middle",
  },
  googleSignInImage: {
    width: 24,
    height: 24,
  },
  googleSignInText: {
    marginLeft: 4,
    fontSize: 20,
    fontWeight: "semibold",
  },
};

