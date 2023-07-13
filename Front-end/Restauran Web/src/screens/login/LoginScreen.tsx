import { useState, useEffect } from "react";
import { GoogleLogin, CredentialResponse } from "@react-oauth/google";
import styles from "./login.module.css";
import { BasicLogin } from "./BasicLogin";
import { LoginResponse, sendJWT } from "../../services/loginService";
import { useNavigate, useLocation } from "react-router-dom";
import { getServerErrorMessage } from "../../services/ErrorHandling";
import { loginScreenImage, storedJWT, storedUserId } from "../constants";
import axios from "axios";

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

  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const fromQRCode = queryParams.get("fromQRCode");

  const responseMessage = async (response: CredentialResponse) => {
    const JWT = response.credential;

    if (JWT) {
      setLoggedIn(true);

      try {
        const responseData: LoginResponse = await sendJWT({
          googleJwt: JWT,
          loginRequestRoleType: "APPEXECUTIVE",
        });
        setLoggedIn(true);
        setUserType(responseData.userType);
        localStorage.setItem("refreshToken", responseData.refreshToken);
        localStorage.setItem("token", responseData.token);
        localStorage.setItem("userId", responseData.appUser.id.toString());

        if (fromQRCode === "true") {
          axios.post(
            `http://localhost:8080/qrcodes/${hashedUrl}`,
            storedUserId,
            {
              headers: {
                Authorization: `Bearer ${storedJWT}`,
              },
            }
          );
          navigate("/menu");
        }
      } catch (err: any) {
        setErrorMsg(getServerErrorMessage(err));
      }
    }
  };

  const errorMessage = () => {
    return "error";
  };

  useEffect(() => {
    // Check the navigation source and redirect accordingly
    if (fromQRCode === "true") {
      navigate("/menu"); // Redirect to the menu page for QR code navigation
    }
  }, [fromQRCode, navigate]);

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
