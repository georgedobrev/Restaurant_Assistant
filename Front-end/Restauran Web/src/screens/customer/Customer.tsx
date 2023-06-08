import { Button, Box } from "@mui/material";
import styles from "./customer.module.css";
// import PersonOutlineIcon from "@mui/icons-material/PersonOutline";
// import AttachMoneyIcon from "@mui/icons-material/AttachMoney";
// import RestaurantMenuIcon from "@mui/icons-material/RestaurantMenu";
import backgroundImage from "../../assets/background.jpg";

const Customer = () => {
  return (
    <Box className={styles.section}>
      <Box
        className={styles.greyPart}
        style={{ backgroundImage: `url(${backgroundImage})` }}
      >
        <Box className={styles.splitLeft}>
          <h2 className={styles.title}>Welcome to our Restaurant</h2>

          <p className={styles.text}>
            Where tradition meets innovation, and fresh ingredients meet
            passionate chefs. Our mission? To provide an unparalleled dining
            experience that starts with incredible cuisine and ends with
            impeccable service.
          </p>
        </Box>

        <Box className={styles.splitRight}>
          <div className={styles.centered}>
            <Button
              variant="contained"
              color="primary"
              // startIcon={<RestaurantMenuIcon />}
              style={{
                marginBottom: "15px",
                width: "100%",
                backgroundColor: "var(--primary-color)",
                color: "var(--black-color)",
                fontWeight: "bold",
              }}
            >
              Request a Menu
            </Button>
            <Button
              variant="contained"
              color="primary"
              // startIcon={<AttachMoneyIcon />}
              style={{
                marginBottom: "15px",
                width: "100%",
                backgroundColor: "var(--primary-color)",
                color: "var(--black-color)",
                fontWeight: "bold",
              }}
            >
              Request a Bill
            </Button>
            <Button
              variant="contained"
              color="primary"
              // startIcon={<PersonOutlineIcon />}
              style={{
                marginBottom: "100px",
                width: "100%",
                backgroundColor: "var(--primary-color)",
                color: "var(--black-color)",
                fontWeight: "bold",
              }}
            >
              Ask for a Waiter
            </Button>

            <Box />
          </div>
        </Box>
      </Box>
      <Box className={styles.whitePart}>
        <h3 className={styles.message}>
          Thank you for choosing Blankfactor's Restaurant. Enjoy your meal!
        </h3>
      </Box>
    </Box>
  );
};

export default Customer;
