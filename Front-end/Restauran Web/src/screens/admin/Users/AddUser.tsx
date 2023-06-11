import { useState } from "react";
import { Button } from "@mui/material";
import styles from "./users.module.css";
import { createUser } from "../../../services/UserService";
import CustomInput from "./UserInputFields";

const AddUser: React.FC = () => {
  const [name, setName] = useState("");
  const [surname, setSurname] = useState("");
  const [email, setEmail] = useState("");

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const userData = {
      name: name,
      surname: surname,
      email: email,
    };

    try {
      const response = await createUser(userData);
      console.log(response);
      setName("");
      setSurname("");
      setEmail("");
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className={styles.container}>
      <h2 className={styles.newUser}>Add new user</h2>

      <form onSubmit={handleSubmit}>
        <div className={styles.userDetails}>
          <div className={styles.flexColumn}>
            <CustomInput
              value={name}
              onChange={setName}
              label="Enter Name"
              fullWidth
              required
              marginBottom={"4%"}
            />
            <CustomInput
              value={surname}
              onChange={setSurname}
              label="Enter Surname"
              fullWidth
              required
              marginBottom={"4%"}
            />
          </div>

          <div className={styles.flexColumn}>
            <CustomInput
              value={email}
              onChange={setEmail}
              label="Enter Email"
              fullWidth
              required
              marginBottom={"1%"}
            />
          </div>
        </div>

        <div className={styles.flexColumn}>
          <Button type="submit" variant="contained" className={styles.button}>
            Add User
          </Button>
        </div>
      </form>
    </div>
  );
};

export default AddUser;
