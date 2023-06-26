import { TextField, Button } from "@mui/material";
import { useState } from "react";
import { LoginScreenProps } from "../../services/loginService";
import styles from "./login.module.css";

export const BasicLogin: React.FC<LoginScreenProps> = ({
  setLoggedIn,
  setUserType,
}) => {
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [errorMsg, setErrorMsg] = useState<string>("");

  const handleUsernameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  const handleFormSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    //TODO implement basic login with back-end
    if (username === "admin" && password === "admin123") {
      setLoggedIn(true);
      setUserType("admin");
    } else if (username === "user" && password === "user123") {
      setLoggedIn(true);
      setUserType("user");
    } else if (username === "sys-admin" && password === "sys-admin123") {
      setLoggedIn(true);
      setUserType("sys-admin");
    } else if (username === "tenant" && password === "tenant123") {
      setLoggedIn(true);
      setUserType("tenant");
    } else {
      setErrorMsg("Wrong username or password");
    }
  };

  return (
    <form onSubmit={handleFormSubmit}>
      <TextField
        label="Username"
        variant="outlined"
        color="warning"
        value={username}
        onChange={handleUsernameChange}
        fullWidth
        required
      />
      <TextField
        label="Password"
        variant="outlined"
        margin="normal"
        type="password"
        color="warning"
        value={password}
        onChange={handlePasswordChange}
        fullWidth
        required
      />
      {errorMsg && <div className={styles.errorMsg}>{errorMsg}</div>}

      <Button
        className={styles.button}
        variant="contained"
        color="primary"
        type="submit"
      >
        Login
      </Button>
    </form>
  );
};
