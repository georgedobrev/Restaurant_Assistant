import React, { useEffect } from "react";
import { View, Text, Image, TouchableOpacity } from "react-native";
import { useNavigation } from "@react-navigation/native";
import * as WebBrowser from "expo-web-browser";
import * as Google from "expo-auth-session/providers/google";
import ImageSection from "./ImageSection";
import BasicLogin from "./BasicLogin";
import googleConfig from "./GoogleConfig";
import { fetchUserInfo } from "./UserService";
import styles from "./stylesLogin";

WebBrowser.maybeCompleteAuthSession();

export const LoginScreen = () => {
  const [user, setUser] = React.useState(null);
  const [accessToken, setAccessToken] = React.useState(null);

  const [request, response, promptAsync] = Google.useAuthRequest({
    clientId: googleConfig.clientId,
    iosClientId: googleConfig.iosCliendId,
    androidClientId: googleConfig.androidClientId,
    scopes: ["openid", "profile", "email"],
  });

  useEffect(() => {
    if (response?.type === "success") {
      const { access_token } = response.params;
      setAccessToken(access_token);

      fetchUserInfo(access_token)
        .then((userInfo) => {
          setUser(userInfo);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, [response]);

  const navigation = useNavigation();

  const onSignInPressed = () => {
    if (user && accessToken) {
      navigation.navigate("Dashboard", { email: user.email, accessToken });
    } else {
      navigation.navigate("Dashboard", { email });
    }
  };

  useEffect(() => {
    if (user && accessToken) {
      onSignInPressed();
    }
  }, [user, accessToken]);

  return (
    <View style={styles.container}>
      <ImageSection />
      <View style={styles.contentContainer}>
        <BasicLogin />

        <Text style={styles.orText}>Or</Text>

        <View>
          <TouchableOpacity
            style={styles.googleSignInContainer}
            disabled={!request}
            onPress={() => {
              promptAsync();
            }}
          >
            <Image
              style={styles.googleSignInImage}
              source={{
                uri: "https://i.ibb.co/j82DCcR/search.png",
              }}
            />
            <Text style={styles.googleSignInText}>Sign in with Google</Text>
          </TouchableOpacity>
        </View>
      </View>
    </View>
  );
};
