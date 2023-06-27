import { useState } from "react";
import { Button, TextField } from "@mui/material";
import styles from "./users.module.css";
import { editUser, User, getUsers } from "../../../services/userService";
import { getServerErrorMessage } from "../../../services/ErrorHandling";

const EditUserComponent: React.FC = () => {
  const [email, setEmail] = useState<string>("");
  const [name, setName] = useState<string>("");
  const [surname, setSurname] = useState<string>("");
  //todo fix with dynamic restaurant ID
  const [restaurantId, setRestaurantId] = useState<number>(1);
  const [roleType, setRoleType] = useState<string>("");
  const [userId, setUserId] = useState<number | undefined>(undefined);
  const [userExists, setUserExists] = useState<boolean>(false);
  const [errorMsg, setErrorMsg] = useState<string>("");

  const handleEditUser = async () => {
    if (userId === undefined) return;
    const userData: User = {
      email,
      name,
      surname,
      roleType,
      restaurant: {
        id: restaurantId,
      },
    };

    try {
      const response: User = await editUser(userData);
      setEmail("");
      setName("");
      setSurname("");
      setRoleType("");
      return response;
    } catch (err: any) {
      setErrorMsg(getServerErrorMessage(err));
    }
  };

  const handleCheckUserExists = async () => {
    try {
      if (userId === undefined) {
        return;
      }

      const user: User | undefined = await getUsers(userId);
      if (user && user.email === email) {
        setUserExists(true);
        setName(user.name);
        setSurname(user.surname);
        setEmail(user.email);
        setRoleType(user.roleType);
      } else {
        setUserExists(false);
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
            label="User ID"
            color="warning"
            margin="normal"
            value={userId}
            onChange={(e) => setUserId(parseInt(e.target.value) || undefined)}
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

          <TextField
            label="Role Type"
            color="warning"
            margin="normal"
            value={roleType}
            onChange={(e) => setRoleType(e.target.value)}
            fullWidth
            required
          />

          <Button variant="contained" onClick={handleEditUser}>
            Update User
          </Button>
        </div>
      ) : (
        errorMsg && <p className={styles.errorMsg}>{errorMsg}</p>
      )}
    </div>
  );
};

export default EditUserComponent;
