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
import { Snackbar, Alert } from "@mui/material";

const EditRestaurant: React.FC = () => {
  const [name, setName] = useState<string>("");
  const [address, setAddress] = useState<string>("");
  const [tablesCount, setTablesCount] = useState<number>(0);
  const [phoneNumber1, setPhoneNumber1] = useState<string>("");
  const [errorMsg, setErrorMsg] = useState<string>("");
  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState("");

  useEffect(() => {
    const storedRestaurantIdNum = Number(storedRestaurantID);

    if (!isNaN(storedRestaurantIdNum)) {
      const loadRestaurantData = async () => {
        try {
          const restaurantData = await getRestaurantByID(storedRestaurantIdNum);
          if (restaurantData) {
            setName(restaurantData.name);
            setAddress(restaurantData.address);
            setTablesCount(restaurantData.tablesCount);
            setPhoneNumber1(restaurantData.phoneNumber1);
            setOpenSnackbar(true);
            setSnackbarMessage(`Succesfully updated`);
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

      if (updatedRestaurant) {
        setName("");
        setAddress("");
        setTablesCount(0);
        setPhoneNumber1("");
        setOpenSnackbar(true);
        setSnackbarMessage(
          `Restaurant ${updatedRestaurant.name} has been successfully updated.`
        );
      }

      return updatedRestaurant;
    } catch (err: any) {
      setErrorMsg(getServerErrorMessage(err));
    }
  };

  return (
    <div>
      <h2 className={styles.newRestaurant}>Edit Restaurant</h2>

      <div className={styles.editSection}>
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
      <Snackbar
        open={openSnackbar}
        autoHideDuration={6000}
        onClose={() => setOpenSnackbar(false)}
        anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
      >
        <Alert
          onClose={() => setOpenSnackbar(false)}
          severity="success"
          sx={{ width: "100%" }}
        >
          {snackbarMessage}
        </Alert>
      </Snackbar>
    </div>
  );
};

export default EditRestaurant;
