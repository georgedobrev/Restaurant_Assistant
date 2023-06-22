import { Box } from "@mui/material";
import axios from "axios";
import PersonOutlineIcon from "@mui/icons-material/PersonOutline";
import AttachMoneyIcon from "@mui/icons-material/AttachMoney";
import RestaurantMenuIcon from "@mui/icons-material/RestaurantMenu";
import styles from "./customer.module.css";
import backgroundImage from "../../assets/background.jpg";
import MenuBtns from "./MenuBtns";
import {
  baseUrl,
  notificationEndpoint,
  createNotification,
} from "../../services/config.json";

const Customer = () => {
  const sendRequest = (requestType: string) => {
    axios
      .post(`${baseUrl}${notificationEndpoint}${createNotification}`, {
        id: 1,
        appUser: {
          id: 1,
        },
        appTable: {
          id: 5,
        },
        requestType: requestType, // Use the requestType variable here
        message: `Please ${requestType.toLowerCase()}.`,
      })
      .then((response) => {
        return response;
      })
      .catch((error) => {
        return error;
      });
  };

  return (
    <Box className={styles.section}>
      <Box
        className={styles.greyPart}
        style={{ backgroundImage: `url(${backgroundImage})` }}
      >
        <Box className={styles.splitLeft}>
          <h2 className={styles.title}>Blankfactor</h2>
        </Box>

        <Box className={styles.splitRight}>
          <div className={styles.centered}>
            <MenuBtns
              startIcon={<RestaurantMenuIcon />}
              onClick={() => sendRequest("Menu")}
            >
              Request a Menu
            </MenuBtns>
            <MenuBtns
              startIcon={<AttachMoneyIcon />}
              onClick={() => sendRequest("Waiter")}
            >
              Ask for a Waiter
            </MenuBtns>
            <MenuBtns
              startIcon={<PersonOutlineIcon />}
              onClick={() => sendRequest("Bill")}
            >
              Request a Bill
            </MenuBtns>

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
