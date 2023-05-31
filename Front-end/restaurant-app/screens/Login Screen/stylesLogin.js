import { StyleSheet } from "react-native";
import globalStyles from "../globalStyles";

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: globalStyles.brownColor,
  },
  contentContainer: {
    flex: 1,
    backgroundColor: globalStyles.bgColor,
    paddingHorizontal: 30,
    borderTopLeftRadius: 40,
    borderTopRightRadius: 40,
  },
  orText: {
    textAlign: "center",
    paddingTop: 4,
    fontWeight: "bold",
    fontSize: 20,
    marginVertical: 10,
  },
  googleSignInContainer: {
    backgroundColor: globalStyles.bgColor,
    borderRadius: 4,
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

  //BasicLogin screen
  inputContainer: {
    marginBottom: 10,
    marginTop: 20,
  },
  textInput: {
    padding: 4,
    backgroundColor: globalStyles.lightColor,
    color: globalStyles.blackColor,
    borderRadius: 10,
    marginBottom: 4,
    marginTop: 4,
    padding: 12,
  },
  forgotPassword: {
    flexDirection: "row",
    justifyContent: "flex-end",
    marginBottom: 4,
  },
  loginButton: {
    padding: 10,
    backgroundColor: globalStyles.yellowColor,
    borderRadius: 20,
    marginTop: 10,
    shadowColor: "black",
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.3,
    shadowRadius: 2,
    elevation: 4,
  },
  loginButtonText: {
    fontSize: 16,
    fontWeight: "bold",
    color: globalStyles.blackColor,
    textAlign: "center",
  },
  labelText: {
    color: globalStyles.blackColor,
    marginLeft: 4,
  },
  passwordLabelText: {
    marginTop: 10,
  },
  forgotPasswordText: {
    color: globalStyles.blackColor,
  },
  errorText: {
    color: globalStyles.redColor,
    marginTop: 4,
  },
});

export default styles;
