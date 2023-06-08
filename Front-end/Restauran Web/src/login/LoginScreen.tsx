import { useState } from "react";
import { TextField, Button, Typography } from "@mui/material";
import "./login.css";

interface LoginScreenProps {
  setLoggedIn: (loggedIn: boolean) => void;
  setUserType: (userType: string) => void;
}

const LoginScreen: React.FC<LoginScreenProps> = ({
  setLoggedIn,
  setUserType,
}) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleUsernameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  const handleFormSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    if (username === "admin" && password === "admin123") {
      setLoggedIn(true);
      setUserType("admin");
    } else {
      console.log("Invalid username or password");
    }
  };

  return (
    <div className="login-container">
      <div className="image-section">
        <h2 className="title-over-image">Food Paradise</h2>
        <img src="src/assets/restaurant-banner.jpeg" alt="Restaurant" />
      </div>

      <div className="form-section">
        <h2 className="title">Welcome</h2>
        <form onSubmit={handleFormSubmit}>
          <TextField
            label="Username"
            variant="outlined"
            value={username}
            onChange={handleUsernameChange}
            fullWidth
            margin="normal"
            required
            color="warning"
          />
          <TextField
            label="Password"
            variant="outlined"
            value={password}
            onChange={handlePasswordChange}
            fullWidth
            margin="normal"
            type="password"
            required
            color="warning"
          />
          <Button
            variant="contained"
            color="primary"
            type="submit"
            style={{
              backgroundColor: "var(--primary-color)",
              color: "var(--black-color)",
              fontWeight: "bold",
              marginTop: 10,
              width: "50%",
              marginLeft: "auto",
              marginRight: "auto",
              display: "block",
            }}
          >
            Login
          </Button>
          <div className="googleBtn">
            <Button
              variant="text"
              startIcon={
                <img
                  src="https://i.ibb.co/j82DCcR/search.png"
                  alt="Google Sign in"
                  style={{ width: 24, height: 24 }}
                />
              }
              style={{
                marginTop: 10,
                marginBottom: 10,
                textTransform: "none",
                color: "var(--primary-color)",
              }}
            >
              <Typography
                style={{ color: "var(--black-color)", fontSize: "1.2rem" }}
              >
                Sign in with Google
              </Typography>
            </Button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default LoginScreen;
