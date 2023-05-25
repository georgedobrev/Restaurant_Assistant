import React, { useEffect } from "react";
import {
  View,
  Text,
  Image,
  TextInput,
  TouchableOpacity,
  StyleSheet,
} from "react-native";
import * as WebBrowser from "expo-web-browser";
import ImageSection from "./ImageSection";

WebBrowser.maybeCompleteAuthSession();

export function LoginScreen({}) {
  const onSignInPressed = () => {
    navigation.navigate("Dashboard");
  };

  return (
    <View style={styles.container}>
      <ImageSection />
      <View style={styles.mainContainer}>
        <View style={{ marginBottom: 20, marginTop: 30 }}>
          <Text style={{ color: "#333", marginLeft: 4 }}>Email address</Text>
          <TextInput style={styles.input} placeholder="Enter email" />

          <Text style={{ color: "#333", marginLeft: 4, marginTop: 10 }}>
            Password
          </Text>
          <TextInput
            style={styles.input}
            placeholder="Enter email"
            secureTextEntry
          />

          <TouchableOpacity style={styles.forgotPasswordText}>
            <Text>Forgot password?</Text>
          </TouchableOpacity>

          <TouchableOpacity
            style={styles.loginButton}
            onPress={onSignInPressed}
          >
            <Text style={styles.loginButtonText}>Login</Text>
          </TouchableOpacity>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#615149",
  },
  mainContainer: {
    flex: 1,
    backgroundColor: "white",
    paddingHorizontal: 30,
    borderTopLeftRadius: 40,
    borderTopRightRadius: 40,
  },
  input: {
    padding: 4,
    backgroundColor: "#F6F1E9",
    color: "#333",
    borderRadius: 10,
    marginBottom: 3,
    marginTop: 5,
    padding: 12,
  },
  forgotPasswordText: {
    flexDirection: "row",
    justifyContent: "flex-end",
    marginBottom: 5,
  },
  loginButton: {
    padding: 10,
    backgroundColor: "#ecd282",
    borderRadius: 20,
    marginTop: 10,
    shadowColor: "black",
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.3,
    shadowRadius: 2,
    elevation: 3,
  },
  loginButtonText: {
    fontSize: 16,
    fontWeight: "bold",
    color: "#000",
    textAlign: "center",
  },
  orText: {
    textAlign: "center",
    paddingTop: 5,
    fontWeight: "bold",
    fontSize: 20,
    marginVertical: 20,
  },
  googleSignInButton: {
    backgroundColor: "white",
    borderRadius: 5,
    paddingHorizontal: 6,
    paddingVertical: 6,
    flexDirection: "row",
    justifyContent: "center",
    alignItems: "middle",
  },
  googleSignInText: {
    marginLeft: 4,
    fontSize: 20,
    fontWeight: "semibold",
  },
});
