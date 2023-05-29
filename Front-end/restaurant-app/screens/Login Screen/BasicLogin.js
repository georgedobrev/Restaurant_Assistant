import React, { useState } from "react";
import { View, Text, TextInput, TouchableOpacity } from "react-native";
import { useNavigation } from "@react-navigation/core";
import GlobalStyles from "../GlobalStyles";

const BasicLogin = () => {
  const [password, setPassword] = useState("");
  const [passwordError, setPasswordError] = useState("");

  const navigation = useNavigation();

  const validatePassword = (password) => {
    const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/;
    if (!passwordPattern.test(password)) {
      setPasswordError(
        "Password must be at least 6 characters long and contain both letters and numbers."
      );
      return false;
    }
    setPasswordError("");
    return true;
  };

  const onSignInPressed = () => {
    const isValidPassword = validatePassword(password);
    if (isValidPassword) {
      navigation.navigate("Dashboard");
    }
  };

  return (
    <View style={styles.inputContainer}>
      <Text style={styles.labelText}>Email address</Text>
      <TextInput style={styles.textInput} placeholder="Enter email" />

      <Text style={[styles.labelText, styles.passwordLabelText]}>Password</Text>
      <TextInput
        style={styles.textInput}
        placeholder="Enter password"
        secureTextEntry
        onChangeText={setPassword}
        value={password}
      />
      {passwordError ? (
        <Text style={styles.errorText}>{passwordError}</Text>
      ) : null}

      <TouchableOpacity style={styles.forgotPassword}>
        <Text style={styles.forgotPasswordText}>Forgot password?</Text>
      </TouchableOpacity>

      <TouchableOpacity style={styles.loginButton} onPress={onSignInPressed}>
        <Text style={styles.loginButtonText}>Login</Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = {
  inputContainer: {
    marginBottom: 10,
    marginTop: 20,
  },
  textInput: {
    padding: 4,
    backgroundColor: GlobalStyles.lightColor,
    color: "#333",
    borderRadius: 10,
    marginBottom: 3,
    marginTop: 5,
    padding: 12,
  },
  forgotPassword: {
    flexDirection: "row",
    justifyContent: "flex-end",
    marginBottom: 5,
  },
  loginButton: {
    padding: 10,
    backgroundColor: GlobalStyles.yellowColor,
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
  labelText: {
    color: "#333",
    marginLeft: 4,
  },
  passwordLabelText: {
    marginTop: 10,
  },
  forgotPasswordText: {
    color: "#333",
  },
  errorText: {
    color: "red",
    marginTop: 5,
  },
};

export default BasicLogin;
