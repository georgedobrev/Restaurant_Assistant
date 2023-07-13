import { useState } from "react";
import { Button, TextField } from "@mui/material";
import styles from "./employees.module.css";
import { getServerErrorMessage } from "../../../services/ErrorHandling";
import { storedJWT } from "../../constants";
import { Snackbar, Alert } from "@mui/material";

const EditEmployee: React.FC = () => {
  const [email, setEmail] = useState<string>("");
  const [name, setName] = useState<string>("");
  const [surname, setSurname] = useState<string>("");
  const [roleType, setRoleType] = useState<string>("");
  const [userExists, setUserExists] = useState<boolean>(false);
  const [errorMsg, setErrorMsg] = useState<string>("");
  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState("");

  const handleEditUser = async () => {
    const userData = {
      email,
      name,
      surname,
      roleType,
      restaurant: {
        id: 1, // Update with the appropriate restaurant ID
      },
    };

    try {
      const response = await fetch(`http://localhost:8080/users/details`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${storedJWT}`,
        },
        body: JSON.stringify(userData),
      });

      if (response.ok) {
        setEmail("");
        setName("");
        setSurname("");
        setRoleType("");
        setOpenSnackbar(true);
        setSnackbarMessage(`Successfully edited ${email}`);
        return response.json();
      } else {
        throw new Error("Failed to update user.");
      }
    } catch (err: any) {
      setErrorMsg(getServerErrorMessage(err));
    }
  };

  const handleCheckUserExists = async () => {
    try {
      const response = await fetch(`http://localhost:8080/users/${email}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${storedJWT}`,
        },
      });

      if (response.ok) {
        const user = await response.json();
        if (user && user.email === email) {
          setUserExists(true);
          setName(user.name);
          setSurname(user.surname);
          setEmail(user.email);
        } else {
          setUserExists(false);
        }
      } else {
        throw new Error("Failed to check user existence.");
      }
    } catch (err: any) {
      setErrorMsg(getServerErrorMessage(err));
    }
  };

  return (
    <div>
      <h2 className={styles.newUser}>Edit Employee</h2>

      {!userExists ? (
        <>
          <TextField
            label="Email"
            color="warning"
            margin="normal"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            fullWidth
            required
          />

          <Button
            className={styles.btns}
            variant="contained"
            onClick={handleCheckUserExists}
          >
            Check User Existence
          </Button>
        </>
      ) : null}

      {userExists ? (
        <div>
          <TextField
            label="Name"
            color="warning"
            margin="normal"
            value={name}
            onChange={(e) => setName(e.target.value)}
            fullWidth
            required
          />

          <TextField
            label="Surname"
            color="warning"
            margin="normal"
            value={surname}
            onChange={(e) => setSurname(e.target.value)}
            fullWidth
            required
          />

          <TextField
            label="Email"
            color="warning"
            margin="normal"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            fullWidth
            required
          />

          <Button
            variant="contained"
            className={styles.btns}
            onClick={handleEditUser}
          >
            Update User
          </Button>
        </div>
      ) : (
        errorMsg && <p className={styles.errorMsg}>{errorMsg}</p>
      )}

      <Snackbar
        open={openSnackbar}
        autoHideDuration={6000}
        onClose={() => setOpenSnackbar(false)}
        anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
      >
        <Alert
          onClose={() => setOpenSnackbar(false)}
          severity="success"
          sx={{ width: "100%" }}
        >
          {snackbarMessage}
        </Alert>
      </Snackbar>
    </div>
  );
};

export default EditEmployee;
