import { useState } from "react";
import styles from "./restaurant.module.css";
import { Button, TextField } from "@mui/material";
import {
  getRestaurantByID,
  editRestaurant,
} from "../../../services/restaurantService";
import { Restaurant } from "../../../services/restaurantService";
import { status } from "../../constants";
import { getServerErrorMessage } from "../../../services/ErrorHandling";

const EditRestaurantComponent: React.FC = () => {
  const [name, setName] = useState<string>("");
  const [address, setAddress] = useState<string>("");
  const [tablesCount, setTablesCount] = useState<number>(0);
  const [phoneNumber1, setPhoneNumber1] = useState<string>("");
  const [restaurantId, setRestaurantId] = useState<string>("");
  const [restaurantExists, setRestaurantExists] = useState<boolean>(false);
  const [errorMsg, setErrorMsg] = useState<string>("");

  const handleEditRestaurant = async () => {
    try {
      const updatedRestaurant: Restaurant | undefined = await editRestaurant({
        id: parseInt(restaurantId),
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

  const handleCheckRestaurantExists = async () => {
    if (restaurantId === null) return;

    try {
      const restaurantData: Restaurant | undefined = await getRestaurantByID(
        parseInt(restaurantId)
      );

      if (restaurantData && restaurantData.id === parseInt(restaurantId)) {
        setRestaurantExists(true);
        setName(restaurantData.name);
        setAddress(restaurantData.address);
        setTablesCount(restaurantData.tablesCount);
        setPhoneNumber1(restaurantData.phoneNumber1);
      } else {
        setRestaurantExists(false);
      }
    } catch (err: any) {
      setErrorMsg(getServerErrorMessage(err));
    }
  };

  return (
    <div>
      <h2 className={styles.newRestaurant}>Edit Restaurant</h2>

      {!restaurantExists ? (
        <>
          <TextField
            label="Restaurant ID"
            value={restaurantId ?? ""}
            onChange={(e) => {
              setRestaurantId(e.target.value);
            }}
            color="warning"
            fullWidth
            required
            margin="normal"
          />

          <Button
            className={styles.btns}
            variant="contained"
            onClick={handleCheckRestaurantExists}
          >
            Check Restaurant Existence
          </Button>
        </>
      ) : null}

      {restaurantExists ? (
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
            label="Address"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
            color="warning"
            fullWidth
            required
            margin="normal"
          />

          <TextField
            label="Tables Count"
            value={tablesCount}
            onChange={(e) => {
              const value = parseInt(e.target.value);
              setTablesCount(isNaN(value) ? 0 : value);
            }}
            color="warning"
            fullWidth
            required
            margin="normal"
          />

          <TextField
            label="Phone Number"
            value={phoneNumber1}
            onChange={(e) => setPhoneNumber1(e.target.value)}
            color="warning"
            fullWidth
            required
            margin="normal"
          />

          <Button variant="contained" onClick={handleEditRestaurant}>
            Update Restaurant
          </Button>
        </div>
      ) : (
        errorMsg && <p className={styles.errorMsg}>{errorMsg}</p>
      )}
    </div>
  );
};

export default EditRestaurantComponent;
