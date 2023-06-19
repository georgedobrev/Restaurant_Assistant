import { useState } from "react";
import { Button, TextField } from "@mui/material";
import styles from "./users.module.css";
import { createUser, User } from "../../../services/UserService";

const AddUser: React.FC = () => {
  const [name, setName] = useState<string>("");
  const [surname, setSurname] = useState<string>("");
  const [email, setEmail] = useState<string>("");

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const userData: User = {
      name,
      surname,
      email,
    };

    try {
      const response: User = await createUser(userData);
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
      <h2 className={styles.newUser}>Add new user</h2>

      <form className={styles.submitForm} onSubmit={handleSubmit}>
        <TextField
          label="Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          color="warning"
          required
          margin="normal"
          className={styles.inputFields}
        />

        <TextField
          label="Surname"
          value={surname}
          onChange={(e) => setSurname(e.target.value)}
          required
          color="warning"
          margin="normal"
          className={styles.inputFields}
        />

        <TextField
          label="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          color="warning"
          margin="normal"
          className={styles.inputFields}
        />

        <Button type="submit" className={styles.btn} variant="contained">
          Add new user
        </Button>
      </form>
    </div>
  );
};

export default AddUser;
