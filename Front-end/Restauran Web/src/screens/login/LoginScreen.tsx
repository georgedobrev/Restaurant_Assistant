import { GoogleLogin, CredentialResponse } from "@react-oauth/google";
import styles from "./login.module.css";
import { BasicLogin } from "./BasicLogin";
import { LoginResponse, sendJWT } from "../../services/loginService";
import { useNavigate } from "react-router-dom";

interface LoginScreenProps {
  setLoggedIn: (loggedIn: boolean) => void;
  setUserType: (userType: string) => void;
}

const LoginScreen: React.FC<LoginScreenProps> = ({
  setLoggedIn,
  setUserType,
}) => {
  const navigate = useNavigate();
  const responseMessage = async (response: CredentialResponse) => {
    console.log(response);
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
        return navigate("/dashboard");
      } catch (error) {
        return error;
      }
    } else {
      return "JWT is undefiend";
    }
  };

  const errorMessage = () => {
    return "error";
  };

  return (
    <div className={styles.loginContainer}>
      <div className={styles.imageSection}>
        <h2 className={styles.titleOverImage}>Food Paradise</h2>
        <img src="src/assets/restaurant-banner.jpeg" alt="Restaurant" />
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