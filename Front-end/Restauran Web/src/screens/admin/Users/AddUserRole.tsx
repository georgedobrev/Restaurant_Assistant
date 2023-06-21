import { useState } from "react";
import { Button, TextField } from "@mui/material";
import styles from "./users.module.css";
import { Roles, addUserRole } from "../../../services/userService";

const AddRoles: React.FC = () => {
  const [name, setName] = useState<string>("");
  const [surname, setSurname] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [roleType, setRoleType] = useState<string>("WAITER");
  const [restaurant, setRestaurant] = useState<{ id: number }>({ id: 0 });

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const rolesData: Roles = {
      name,
      surname,
      email,
      roleType,
      restaurant,
    };

    try {
      const response: Roles = await addUserRole(rolesData);
      setName("");
      setSurname("");
      setEmail("");
      return response;
    } catch (error) {
      return error;
    }
  };

  return (
    <div>
      <h2 className={styles.newUser}>Add role type</h2>

      <form className={styles.submitForm} onSubmit={handleSubmit}>

        <TextField
          label="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          color="warning"
          margin="normal"
          className={styles.inputFields}
        />

        <TextField
          label="User role"
          value={roleType}
          onChange={(e) => setRoleType(e.target.value)}
          required
          color="warning"
          margin="normal"
          className={styles.inputFields}
        />

        <TextField
          label="Restaurant ID"
          value={restaurant.id}
          onChange={(e) =>
            setRestaurant({ id: parseInt(e.target.value) || 0 })
          }
          required
          color="warning"
          margin="normal"
          className={styles.inputFields}
        />

        <Button type="submit" className={styles.btn} variant="contained">
          Add user Role
        </Button>
      </form>
    </div>
  );
};

export default AddRoles;
