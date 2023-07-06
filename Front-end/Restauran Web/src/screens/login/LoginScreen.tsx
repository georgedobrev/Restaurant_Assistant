import { useState } from "react";
import { GoogleLogin, CredentialResponse } from "@react-oauth/google";
import styles from "./login.module.css";
import { BasicLogin } from "./BasicLogin";
import { LoginResponse, sendJWT } from "../../services/loginService";
import { useNavigate } from "react-router-dom";
import { getServerErrorMessage } from "../../services/ErrorHandling";
import { loginScreenImage } from "../constants";

interface LoginScreenProps {
  setLoggedIn: (loggedIn: boolean) => void;
  setUserType: (userType: string) => void;
}

const LoginScreen: React.FC<LoginScreenProps> = ({
  setLoggedIn,
  setUserType,
}) => {
  const [errorMsg, setErrorMsg] = useState<string>("");
  const navigate = useNavigate();
  const responseMessage = async (response: CredentialResponse) => {
    const JWT = response.credential;

    if (JWT) {
      setLoggedIn(true);

      try {
        const responseData: LoginResponse = await sendJWT({ googleJwt: JWT });
        setLoggedIn(true);
        setUserType(responseData.userType);
        localStorage.setItem("refreshToken", responseData.refreshToken);
        localStorage.setItem("token", responseData.token);
        localStorage.setItem("userId", responseData.appUser.id.toString());
        navigate("/restaurants");
      } catch (err: any) {
        setErrorMsg(getServerErrorMessage(err));
      }
    }
  };

  const errorMessage = () => {
    return "error";
  };

  return (
    <div className={styles.loginContainer}>
      <div className={styles.imageSection}>
        <h2 className={styles.titleOverImage}>Food Paradise</h2>
        <img src={loginScreenImage} alt="Restaurant" />
      </div>

      <div className={styles.formSection}>
        <h2 className={styles.title}>Welcome</h2>
        <BasicLogin setLoggedIn={setLoggedIn} setUserType={setUserType} />
        <GoogleLogin onSuccess={responseMessage} onError={errorMessage} />
      </div>
    </div>
  );
};

export default LoginScreen;
