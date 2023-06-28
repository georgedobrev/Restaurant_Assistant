import { useState, useEffect } from "react";
import { Button, TextField } from "@mui/material";
import { getServerErrorMessage } from "../../../services/ErrorHandling";
import { Restaurant } from "../../../services/restaurantService";
import { storedRestaurantID } from "../../constants";
import styles from "./restaurant.module.css";
import {
  getRestaurantByID,
  editRestaurant,
} from "../../../services/restaurantService";

const EditRestaurantComponent: React.FC = () => {
  const [name, setName] = useState<string>("");
  const [address, setAddress] = useState<string>("");
  const [tablesCount, setTablesCount] = useState<number>(0);
  const [phoneNumber1, setPhoneNumber1] = useState<string>("");
  const [errorMsg, setErrorMsg] = useState<string>("");

  useEffect(() => {
    const storedRestaurantIdNum = Number(storedRestaurantID);

    if (!isNaN(storedRestaurantIdNum)) {
      const loadRestaurantData = async () => {
        try {
          const [restaurantData]: Restaurant[] | undefined =
            await getRestaurantByID(storedRestaurantIdNum);
          await getRestaurantByID(storedRestaurantIdNum);
          if (restaurantData) {
            setName(restaurantData.name);
            setAddress(restaurantData.address);
            setTablesCount(restaurantData.tablesCount);
            setPhoneNumber1(restaurantData.phoneNumber1);
          }
        } catch (err: any) {
          setErrorMsg(getServerErrorMessage(err));
        }
      };
      loadRestaurantData();
    }
  }, []);

  const handleEditRestaurant = async () => {
    const storedRestaurantIdNum = Number(storedRestaurantID);

    if (isNaN(storedRestaurantIdNum)) {
      return "Error: storedRestaurantID not found in localStorage";
    }

    try {
      const updatedRestaurant: Restaurant | undefined = await editRestaurant({
        id: storedRestaurantIdNum,
        name,
        address,
        tablesCount,
        phoneNumber1,
      });

      setName("");
      setAddress("");
      setTablesCount(0);
      setPhoneNumber1("");
      return updatedRestaurant;
    } catch (err: any) {
      setErrorMsg(getServerErrorMessage(err));
    }
  };
  return (
    <div>
      <h2 className={styles.newRestaurant}>Edit Restaurant</h2>

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
          label="Address"
          color="warning"
          margin="normal"
          value={address}
          onChange={(e) => setAddress(e.target.value)}
          fullWidth
          required
        />

        <TextField
          label="Tables Count"
          color="warning"
          margin="normal"
          value={tablesCount}
          onChange={(e) => {
            const value = parseInt(e.target.value);
            setTablesCount(isNaN(value) ? 0 : value);
          }}
          fullWidth
          required
        />

        <TextField
          label="Phone Number"
          color="warning"
          margin="normal"
          value={phoneNumber1}
          onChange={(e) => setPhoneNumber1(e.target.value)}
          fullWidth
          required
        />

        <Button
          className={styles.btns}
          variant="contained"
          onClick={handleEditRestaurant}
        >
          Update Restaurant
        </Button>
      </div>

      {errorMsg && <p className={styles.errorMsg}>{errorMsg}</p>}
    </div>
  );
};

export default EditRestaurantComponent;
