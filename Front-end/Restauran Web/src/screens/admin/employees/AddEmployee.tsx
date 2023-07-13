import { useState } from "react";
import {
  Button,
  FormControl,
  InputLabel,
  Select,
  TextField,
  MenuItem,
} from "@mui/material";
import styles from "./employees.module.css";
import { addEmployee } from "../../../services/userService";
import { getServerErrorMessage } from "../../../services/ErrorHandling";
import { storedRestaurantID } from "../../constants";
import { Snackbar, Alert } from "@mui/material";
const AddEmployee: React.FC = () => {
  const [email, setEmail] = useState<string>("");
  const [roleType, setRoleType] = useState<string>("");
  const [errorMsg, setErrorMsg] = useState<string>("");
  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState("");

  const restaurantID: number = parseInt(storedRestaurantID || "0");

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setErrorMsg("");

    const user = {
      email,
      roleType,
      restaurant: { id: restaurantID },
    };

    try {
      const createdUser = await addEmployee(user);
      setEmail("");
      setRoleType("");
      setOpenSnackbar(true);
      setSnackbarMessage(`Successfully added ${createdUser.email}`);
      return createdUser;
    } catch (err: any) {
      setErrorMsg(getServerErrorMessage(err));
    }
  };

  const handleRoleChange = (event: any) => {
    setRoleType(event.target.value);
  };

  return (
    <div>
      <h2 className={styles.newUser}>Add new employee</h2>

      <form className={styles.submitForm} onSubmit={handleSubmit}>
        <TextField
          label="Email"
          color="warning"
          margin="normal"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          className={styles.inputFields}
        />

        <FormControl fullWidth margin="normal">
          <InputLabel>Role</InputLabel>
          <Select
            label="Role"
            color="warning"
            value={roleType}
            onChange={handleRoleChange}
          >
            <MenuItem value={"ADMIN"}>ADMIN</MenuItem>
            <MenuItem value={"WAITER"}>WAITER</MenuItem>
          </Select>
        </FormControl>

        {errorMsg && <p className={styles.errorMsg}>{errorMsg}</p>}

        <Button type="submit" className={styles.btn} variant="contained">
          Add new employee
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

export default AddEmployee;
