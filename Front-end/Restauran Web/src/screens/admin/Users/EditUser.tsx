import { useState } from "react";
import { Button, TextField } from "@mui/material";
import styles from "./users.module.css";
import { editUser, User, getUsers } from "../../../services/UserService";

const EditUserComponent: React.FC = () => {
  const [name, setName] = useState<string>("");
  const [surname, setSurname] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [userId, setUserId] = useState<string>("");
  const [userExists, setUserExists] = useState<boolean>(false);
  const [requestStatus, setRequestStatus] = useState<string>("idle");

  const handleEditUser = async () => {
    try {
      const updatedUser: User = await editUser(
        {
          id: parseInt(userId),
          name,
          surname,
          email,
          blacklisted: false,
          active: true,
          createdAt: "",
        },
        parseInt(userId)
      );
      return updatedUser;
      setRequestStatus("success");
    } catch (err) {
      setRequestStatus("failure");
    }
  };

  const handleCheckUserExists = async () => {
    try {
      const user: User | undefined = await getUsers(parseInt(userId));

      if (user && user.email === email) {
        setUserExists(true);
        setName(user.name);
        setSurname(user.surname);
        setEmail(user.email);
        setRequestStatus("success");
      } else {
        setUserExists(false);
        setRequestStatus("failure");
      }
    } catch (err) {
      setRequestStatus("failure");
    }
  };

  return (
    <div>
      <h2 className={styles.newUser}>Edit User</h2>

      {!userExists ? (
        <>
          <TextField
            label="User ID"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
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
            value={name}
            onChange={(e) => setName(e.target.value)}
            color="warning"
            fullWidth
            required
            margin="normal"
          />

          <TextField
            label="Surname"
            value={surname}
            onChange={(e) => setSurname(e.target.value)}
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

          <Button variant="contained" onClick={handleEditUser}>
            Update User
          </Button>
        </div>
      ) : (
        requestStatus === "failure" && (
          <p className={styles.errorMsg}>Such user does not exist</p>
        )
      )}
    </div>
  );
};

export default EditUserComponent;
