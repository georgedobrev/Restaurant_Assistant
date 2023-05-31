import React, { useState } from "react";
import { View, Text, TextInput, TouchableOpacity } from "react-native";
import { useNavigation } from "@react-navigation/core";
import styles from "./stylesLogin";

const BasicLogin = () => {
  const [password, setPassword] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [email, setEmail] = useState("");

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
      navigation.navigate("Dashboard", { email });
    }
  };

  return (
    <View style={styles.inputContainer}>
      <Text style={styles.labelText}>Email address</Text>
      <TextInput
        style={styles.textInput}
        placeholder="Enter email"
        onChangeText={setEmail}
        value={email}
      />
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

export default BasicLogin;
