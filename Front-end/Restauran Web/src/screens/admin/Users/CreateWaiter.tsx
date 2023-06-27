import { useState } from "react";
import { Button, TextField } from "@mui/material";
import { getServerErrorMessage } from "../../../services/ErrorHandling";
import styles from "./users.module.css";
import {
  Roles,
  addUserRole,
  createWaiter,
} from "../../../services/userService";

const AddWaiter: React.FC = () => {
  const [email, setEmail] = useState<string>("");
  const [roleType, setRoleType] = useState<string>("WAITER");
  const [restaurant, setRestaurant] = useState<{ id: number }>({ id: 1 });
  const [errorMsg, setErrorMsg] = useState<string>("");

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setErrorMsg("");

    const user: Roles = {
      email,
      roleType,
      restaurant,
    };

    try {
      const createUser: Roles = await createWaiter(user);
      const roleData: Roles = {
        email: createUser.email,
        roleType,
        restaurant,
      };
      const addRole: Roles = await addUserRole(roleData);
      setEmail("");
    } catch (err: any) {
      setErrorMsg(getServerErrorMessage(err));
    }
  };

  return (
    <div>
      <h2 className={styles.newUser}>Add new waiter</h2>

      <form className={styles.submitForm} onSubmit={handleSubmit}>
        <TextField
          label="Email"
          color="warning"
          margin="normal"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          className={styles.inputFields}
        />

        <TextField
          label="User role"
          color="warning"
          margin="normal"
          value={roleType}
          onChange={(e) => setRoleType(e.target.value)}
          required
          className={styles.inputFields}
        />

        <TextField
          label="Restaurant ID"
          color="warning"
          margin="normal"
          value={restaurant.id}
          onChange={(e) => setRestaurant({ id: parseInt(e.target.value) || 0 })}
          required
          className={styles.inputFields}
        />
        {errorMsg && <p className={styles.errorMsg}>{errorMsg}</p>}

        <Button type="submit" className={styles.btn} variant="contained">
          Add new waiter
        </Button>
      </form>
    </div>
  );
};

export default AddWaiter;
