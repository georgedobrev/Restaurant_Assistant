import { useState } from "react";
import { Box } from "@mui/material";
import PersonOutlineIcon from "@mui/icons-material/PersonOutline";
import AttachMoneyIcon from "@mui/icons-material/AttachMoney";
import RestaurantMenuIcon from "@mui/icons-material/RestaurantMenu";
import styles from "./customer.module.css";
import backgroundImage from "../../assets/background.jpg";
import MenuBtns from "./MenuBtns";
import { sendNotification } from "../../services/customerServices";
import { getServerErrorMessage } from "../../services/ErrorHandling";

interface CustomerProps {
  appUserId: number;
  appTableId: number;
}
const Customer: React.FC<CustomerProps> = ({ appUserId, appTableId }) => {
  const [errorMsg, setErrorMsg] = useState<string>("");

  const sendRequest = async (requestType: string) => {
    const message = `Please ${requestType.toLowerCase()}.`;
    try {
      const response = await sendNotification(
        appUserId,
        appTableId,
        requestType,
        message
      );
      return response;
    } catch (err: any) {
      setErrorMsg(getServerErrorMessage(err));
    }
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
            {errorMsg && <p className={styles.errorMsg}>{errorMsg}</p>}

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
