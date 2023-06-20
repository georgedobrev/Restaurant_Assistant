import { useState } from "react";
import styles from "./restaurant.module.css";
import { Button, TextField } from "@mui/material";
import {
  getRestaurantByID,
  editRestaurant,
} from "../../../services/restaurantService";
import { Restaurant } from "../../../services/restaurantService";
import { status } from "../../constants";

const EditRestaurantComponent: React.FC = () => {
  const [name, setName] = useState<string>("");
  const [address, setAddress] = useState<string>("");
  const [tablesCount, setTablesCount] = useState<number>(0);
  const [phoneNumber, setPhoneNumber] = useState<string>("");
  const [restaurantId, setRestaurantId] = useState<string>("");
  const [restaurantExists, setRestaurantExists] = useState<boolean>(false);
  const [requestStatus, setRequestStatus] = useState<string>("idle");

  const handleEditRestaurant = async () => {
    try {
      const updatedRestaurant: Restaurant = await editRestaurant({
        id: parseInt(restaurantId),
        name,
        address,
        tablesCount,
        phoneNumber,
      });
      setRequestStatus(status.successStatus);
      return updatedRestaurant;
    } catch (err) {
      setRequestStatus(status.failureStatus);
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
        setPhoneNumber(restaurantData.phoneNumber);
        setRequestStatus(status.successStatus);
      } else {
        setRestaurantExists(false);
        setRequestStatus(status.failureStatus);
      }
    } catch (err) {
      setRequestStatus(status.failureStatus);
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
            value={phoneNumber}
            onChange={(e) => setPhoneNumber(e.target.value)}
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
        requestStatus === status.failureStatus && (
          <p className={styles.errorMsg}>Such restaurant does not exist</p>
        )
      )}
    </div>
  );
};

export default EditRestaurantComponent;
