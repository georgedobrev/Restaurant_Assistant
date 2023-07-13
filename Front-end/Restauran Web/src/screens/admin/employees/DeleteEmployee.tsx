import { useState } from "react";
import { Button, TextField, Snackbar } from "@mui/material";
import Alert from "@mui/material/Alert";
import { getServerErrorMessage } from "../../../services/ErrorHandling";
import styles from "./employees.module.css";
import { storedJWT } from "../../constants";

const DeleteEmployee: React.FC = () => {
  const [email, setEmail] = useState<string>("");
  const [errorMsg, setErrorMsg] = useState<string>("");
  const [openSnackbar, setOpenSnackbar] = useState(false);

  const handleDeleteUser = async () => {
    try {
      const response = await fetch(`http://localhost:8080/users/${email}`, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${storedJWT}`,
        },
      });

      if (response.ok) {
        setEmail("");
        setOpenSnackbar(true);
      } else {
        throw new Error("Failed to delete user");
      }
    } catch (err: any) {
      setErrorMsg(getServerErrorMessage(err));
    }
  };

  const handleCloseSnackbar = () => {
    setOpenSnackbar(false);
  };

  return (
    <div>
      <h2 className={styles.newUser}>Delete User</h2>

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
        onClick={handleDeleteUser}
      >
        Delete User
      </Button>

      {errorMsg && <p className={styles.errorMsg}>{errorMsg}</p>}

      <Snackbar
        open={openSnackbar}
        autoHideDuration={6000}
        onClose={handleCloseSnackbar}
        anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
      >
        <Alert
          onClose={handleCloseSnackbar}
          severity="success"
          sx={{ width: "100%" }}
        >
          User successfully deleted
        </Alert>
      </Snackbar>
    </div>
  );
};

export default DeleteEmployee;
