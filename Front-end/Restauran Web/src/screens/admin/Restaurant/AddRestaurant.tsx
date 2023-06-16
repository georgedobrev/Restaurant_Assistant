import { useState } from "react";
import { Button, TextField } from "@mui/material";
import styles from "./restaurant.module.css";
import {
  createRestaurant,
  Restaurant,
} from "../../../services/restaurantService";

const AddRestaurant: React.FC = () => {
  const [name, setName] = useState<string>("");
  const [address, setAddress] = useState<string>("");
  const [tablesCount, setTablesCount] = useState<string>("");
  const [phoneNumber, setPhoneNumber] = useState<string>("");

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const restaurantData: Restaurant = {
      name,
      tablesCount: parseInt(tablesCount),
      address,
      phoneNumber,
    };

    try {
      const response: Restaurant = await createRestaurant(restaurantData);
      setName("");
      setAddress("");
      setTablesCount("");
      setPhoneNumber("");
      return response;
    } catch (error) {
      return error;
    }
  };

  return (
    <div>
      <h2 className={styles.newRestaurant}>Add new restaurant</h2>

      <form className={styles.submitForm} onSubmit={handleSubmit}>
        <TextField
          label="Restaurant Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          color="warning"
          required
          margin="normal"
          className={styles.inputFields}
        />

        <TextField
          label="Address"
          value={address}
          onChange={(e) => setAddress(e.target.value)}
          required
          color="warning"
          margin="normal"
          className={styles.inputFields}
        />

        <TextField
          label="Tables Count"
          value={tablesCount}
          onChange={(e) => setTablesCount(e.target.value)}
          required
          color="warning"
          margin="normal"
          className={styles.inputFields}
        />

        <TextField
          label="Phone Number"
          value={phoneNumber}
          onChange={(e) => setPhoneNumber(e.target.value)}
          required
          color="warning"
          margin="normal"
          className={styles.inputFields}
        />

        <Button type="submit" className={styles.btn} variant="contained">
          Add new restaurant
        </Button>
      </form>
    </div>
  );
};

export default AddRestaurant;
