import { useState } from "react";
import { Button, TextField } from "@mui/material";
import styles from "./users.module.css";
import { Roles, addUserRole, createAdmin } from "../../../services/userService";

const AddRoles: React.FC = () => {
  const [name, setName] = useState<string>("");
  const [surname, setSurname] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [roleType, setRoleType] = useState<string>("ADMIN");
  const [restaurant, setRestaurant] = useState<{ id: number }>({ id: 1 });

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const user: Roles = {
      name,
      surname,
      email,
      roleType,
      restaurant
    };

    try {
      const createdUser: Roles = await createAdmin(user);

      const roleData: Roles = {
        name,
        surname,
        email: createdUser.email,
        roleType,
        restaurant,
      };

      try {
        const response: Roles = await addUserRole(roleData);
        setEmail("");
        return response;
      } catch (error) {
        return error;
      }
    } catch (error) {
      return error;
    }
  };

  return (
    <div>
      <h2 className={styles.newUser}>Add new admin</h2>

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
          Add new admin
        </Button>
      </form>
    </div>
  );
};

export default AddRoles;
