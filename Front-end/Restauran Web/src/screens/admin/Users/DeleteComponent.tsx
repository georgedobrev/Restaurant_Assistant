import { useState } from "react";
import { Button, TextField } from "@mui/material";
import styles from "./users.module.css";
import { deleteUser, getUsers, User } from "../../../services/userService";

const DeleteUserComponent: React.FC = () => {
  const [id, setId] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [errorMsg, setErrorMsg] = useState<string>("");

  const handleDeleteUser = async () => {
    setErrorMsg("");

    try {
      const user: User | undefined = await getUsers(parseInt(id));

      if (user && user.email === email) {
        await deleteUser(parseInt(id));
        console.log("User deleted:", user);
        setId("");
        setEmail("");
      } else {
        setErrorMsg("There is no such user");
      }
    } catch (err) {
      setErrorMsg("Error occurred while deleting user");
    }
  };

  return (
    <div>
      <h2 className={styles.newUser}>Delete User</h2>

      <TextField
        label="ID"
        value={id}
        onChange={(e) => setId(e.target.value)}
        color="warning"
        fullWidth
        required
        margin="normal"
      />

      <TextField
        label="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        color="warning"
        fullWidth
        required
        margin="normal"
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
