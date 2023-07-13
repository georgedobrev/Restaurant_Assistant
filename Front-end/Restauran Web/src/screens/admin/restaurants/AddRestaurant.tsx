import { useEffect, useState } from "react";
import { Button, TextField } from "@mui/material";
import styles from "./restaurant.module.css";
import { getServerErrorMessage } from "../../../services/ErrorHandling";
import { storedUserId } from "../../constants";
import {
  createRestaurant,
  Restaurant,
  RestaurantObj,
} from "../../../services/restaurantService";
import { Snackbar, Alert } from "@mui/material";

const AddRestaurant: React.FC = () => {
  const [name, setName] = useState<string>("");
  const [address, setAddress] = useState<string>("");
  const [tablesCount, setTablesCount] = useState<string>("");
  const [phoneNumber1, setPhoneNumber1] = useState<string>("");
  const initialUserId = storedUserId ? parseInt(storedUserId) : 0;
  const [userId, setUserId] = useState<number>(initialUserId);
  const [errorMsg, setErrorMsg] = useState<string>("");
  const [successMsg, setSuccessMsg] = useState<string>(""); // New state variable for success message
  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState("");

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

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
      setOpenSnackbar(true);
      setSnackbarMessage(`${restaurantData.name} has been added successfully`);
    } catch (err: any) {
      setErrorMsg(getServerErrorMessage(err));
    }
  };

  return (
    <div>
      <h2 className={styles.newRestaurant}>Add new restaurant</h2>

      <form className={styles.submitForm} onSubmit={handleSubmit}>
        <TextField
          className={styles.inputFields}
          label="Restaurant Name"
          color="warning"
          margin="normal"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
        <TextField
          className={styles.inputFields}
          label="Address"
          color="warning"
          margin="normal"
          value={address}
          onChange={(e) => setAddress(e.target.value)}
          required
        />
        <TextField
          label="Tables Count"
          color="warning"
          margin="normal"
          value={tablesCount}
          onChange={(e) => setTablesCount(e.target.value)}
          required
          className={styles.inputFields}
        />
        <TextField
          label="Phone Number"
          color="warning"
          margin="normal"
          value={phoneNumber1}
          onChange={(e) => setPhoneNumber1(e.target.value)}
          required
          className={styles.inputFields}
        />
        {/* {errorMsg && <p className={styles.errorMsg}>{errorMsg}</p>} */}

        <Button type="submit" className={styles.btns} variant="contained">
          Add new restaurant
        </Button>
      </form>
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

export default AddRestaurant;
