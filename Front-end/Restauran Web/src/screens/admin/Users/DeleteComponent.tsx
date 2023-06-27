import { useState } from "react";
import { Button, TextField } from "@mui/material";
import styles from "./users.module.css";
import { deleteUser, getUsers, User } from "../../../services/userService";
import { getServerErrorMessage } from "../../../services/ErrorHandling";

const DeleteUserComponent: React.FC = () => {
  const [id, setId] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [errorMsg, setErrorMsg] = useState<string>("");

  const handleDeleteUser = async () => {
    try {
      const user: User | undefined = await getUsers(parseInt(id));
      if (user && user.email === email) {
        await deleteUser(parseInt(id));
        setId("");
        setEmail("");
      }
    } catch (err: any) {
      setErrorMsg(getServerErrorMessage(err));
    }
  };

  return (
    <div>
      <h2 className={styles.newUser}>Delete User</h2>

      <TextField
        label="ID"
        color="warning"
        margin="normal"
        value={id}
        onChange={(e) => setId(e.target.value)}
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
        className={styles.btns}
        variant="contained"
        onClick={handleDeleteUser}
      >
        Delete User
      </Button>

      {errorMsg && <p className={styles.errorMsg}>{errorMsg}</p>}
    </div>
  );
};

export default DeleteUserComponent;
