import { Button, Box, TextField } from "@mui/material";
import styles from "./restaurant.module.css";

const AddRestaurant: React.FC = () => {
  return (
    <div className={styles.container}>
      <h2 className={styles.newRestaurant}>Add restaurant</h2>

      <form>
        <div className={styles.userDetails}>
          <div className={styles.flexColumn}>
            <Box marginBottom={"4%"}>
              <TextField
                className={styles.inputFields}
                label="Restaurant Name"
                variant="outlined"
                size="small"
                color="warning"
                required
                fullWidth
              />
            </Box>

            <Box marginBottom={"4%"}>
              <TextField
                className={styles.inputFields}
                id="filled-basic"
                label="Tables Count"
                variant="outlined"
                size="small"
                color="warning"
                required
                fullWidth
              />
            </Box>

            <Box marginBottom={"4%"}>
              <TextField
                className={styles.inputFields}
                id="filled-basic"
                label="Phone Number"
                variant="outlined"
                size="small"
                color="warning"
                required
                fullWidth
              />
            </Box>
          </div>

          <div className={styles.flexColumn}>
            <Box marginBottom={"4%"}>
              <TextField
                className={styles.inputFields}
                id="filled-basic"
                label="Address"
                variant="outlined"
                size="small"
                color="warning"
                required
                fullWidth
              >
                Number
              </TextField>
            </Box>

            <Box marginBottom={"4%"}>
              <TextField
                className={styles.inputFields}
                id="filled-basic"
                label="Email Address"
                variant="outlined"
                size="small"
                color="warning"
                required
                fullWidth
              />
            </Box>

            <Box marginBottom={"4%"}>
              <TextField
                className={styles.inputFields}
                id="filled-basic"
                label="Phone Number 2"
                variant="outlined"
                size="small"
                color="warning"
                required
                fullWidth
              >
                Number
              </TextField>
            </Box>
          </div>
        </div>
        <div className={styles.flexColumn}>
          <Button className={styles.button} variant="contained">
            Add Restaurant
          </Button>
        </div>
      </form>
    </div>
  );
};

export default AddRestaurant;
