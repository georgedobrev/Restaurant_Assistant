import { useState } from "react";
import { Button, TextField } from "@mui/material";
import styles from "./users.module.css";
import { editUser, User, getUsers } from "../../../services/userService";
import { status } from "../../constants";

const EditUserComponent: React.FC = () => {
  const [email, setEmail] = useState<string>("");
  const [name, setName] = useState<string>("");
  const [surname, setSurname] = useState<string>("");
  //todo fix with dynamic restairant ID
  const [restaurantId, setRestaurantId] = useState<number>(1);
  const [roleType, setRoleType] = useState<string>("");
  const [userId, setUserId] = useState<number | undefined>(undefined);
  const [userExists, setUserExists] = useState<boolean>(false);
  const [requestStatus, setRequestStatus] = useState<string>("idle");

  const handleEditUser = async () => {
    if (userId === undefined) return; 
  
    const userData: User = {
      email,
      name,
      surname,
      roleType,
      restaurant: {
        id: restaurantId,
      }
    };
  
    try {
      const response: User = await editUser(userData);
      setEmail("");
      setName("");
      setSurname("");
      setRoleType("")
      return response;
    } catch (error) {
      console.error(error);
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
        setRequestStatus(status.successStatus);
      } else {
        setUserExists(false);
        setRequestStatus(status.failureStatus);
      }
    } catch (err) {
      setRequestStatus(status.failureStatus);
    }
  };
  

  return (
    <div>
      <h2 className={styles.newUser}>Edit Employee</h2>

      {!userExists ? (
        <>
          <TextField
            label="User ID"
            value={userId}
            onChange={(e) => setUserId(parseInt(e.target.value) || undefined)}
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

          <TextField
            label="Role Type"
            value={roleType}
            onChange={(e) => setRoleType(e.target.value)}
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
        requestStatus === status.failureStatus && (
          <p className={styles.errorMsg}>Such user does not exist</p>
        )
      )}
    </div>
  );
};

export default EditUserComponent;
