import { useState } from "react";
import { Button, TextField } from "@mui/material";
import styles from "./restaurant.module.css";
import {
  createRestaurant,
  Restaurant,
  RestaurantObj,
} from "../../../services/restaurantService";
import { storedUserId } from "../../constants";
import { getServerErrorMessage } from "../../../services/ErrorHandling";

const AddRestaurant: React.FC = () => {
  const [name, setName] = useState<string>("");
  const [address, setAddress] = useState<string>("");
  const [tablesCount, setTablesCount] = useState<string>("");
  const [phoneNumber1, setPhoneNumber1] = useState<string>("");
  const initialUserId = storedUserId ? parseInt(storedUserId) : 0;
  const [userId, setUserId] = useState<number>(initialUserId);
  const [errorMsg, setErrorMsg] = useState<string>("");

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setErrorMsg("");

    const restaurantData: Restaurant = {
      name,
      tablesCount: parseInt(tablesCount),
      address,
      phoneNumber1,
    };

    const restaurantObj: RestaurantObj = {
      restaurantDto: restaurantData,
      userId,
    };

    try {
      const response: RestaurantObj = await createRestaurant(restaurantObj);
      setName("");
      setAddress("");
      setTablesCount("");
      setPhoneNumber1("");
      return response;
    } catch (err: any) {
      setErrorMsg(getServerErrorMessage(err));
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
          value={phoneNumber1}
          onChange={(e) => setPhoneNumber1(e.target.value)}
          required
          color="warning"
          margin="normal"
          className={styles.inputFields}
        />
        {errorMsg && <p className={styles.errorMsg}>{errorMsg}</p>}

        <Button type="submit" className={styles.btn} variant="contained">
          Add new restaurant
        </Button>
      </form>
    </div>
  );
};

export default AddRestaurant;
