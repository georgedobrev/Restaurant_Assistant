import { useState } from "react";
import { Button, TextField } from "@mui/material";
import styles from "./employees.module.css";
import { Roles, addUserRole, createAdmin } from "../../../services/userService";
import { getServerErrorMessage } from "../../../services/ErrorHandling";

const AddAdmin: React.FC = () => {
  const [email, setEmail] = useState<string>("");
  const [roleType, setRoleType] = useState<string>("ADMIN");
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
      const createdUser: Roles = await createAdmin(user);
      const roleData: Roles = {
        email: createdUser.email,
        roleType,
        restaurant,
      };

      const response: Roles = await addUserRole(roleData);
      setEmail("");
      return response;
    } catch (err: any) {
      setErrorMsg(getServerErrorMessage(err));
    }
  };

  return (
    <div>
      <h2 className={styles.newUser}>Add new admin</h2>

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
          Add new admin
        </Button>
      </form>
    </div>
  );
};

export default AddAdmin;
