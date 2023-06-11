import { useState } from "react";
import { Button } from "@mui/material";
import { createRestaurant } from "../../../services/restaurantService";
import styles from "./restaurant.module.css";
import RestaurantInputFields from "./RestaurantInputFields";

const AddRestaurant: React.FC = () => {
  const [name, setName] = useState("");
  const [tablesCount, setTablesCount] = useState("");
  const [address, setAddress] = useState("");
  const [phoneNumber1, setPhoneNumber1] = useState("");
  const [phoneNumber2, setPhoneNumber2] = useState("");

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const restaurantData = {
      name: name,
      address: address,
      tablesCount: tablesCount,
      phoneNumber1: phoneNumber1,
      phoneNumber2: phoneNumber2,
    };

    try {
      const response = await createRestaurant(restaurantData);
      console.log(response);
      setName("");
      setTablesCount("");
      setAddress("");
      setPhoneNumber1("");
      setPhoneNumber2("");
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className={styles.container}>
      <h2 className={styles.newRestaurant}>Add restaurant</h2>

      <form onSubmit={handleSubmit}>
        <div className={styles.userDetails}>
          <div className={styles.flexColumn}>
            <RestaurantInputFields
              label="Restaurant Name"
              value={name}
              setValue={setName}
            />

            <RestaurantInputFields
              label="Tables Count"
              value={tablesCount}
              setValue={setTablesCount}
            />

            <RestaurantInputFields
              label="Phone Number"
              value={phoneNumber1}
              setValue={setPhoneNumber1}
            />
          </div>

          <div className={styles.flexColumn}>
            <RestaurantInputFields
              label="Address"
              value={address}
              setValue={setAddress}
            />

            <RestaurantInputFields
              label="Phone Number 2"
              value={phoneNumber2}
              setValue={setPhoneNumber2}
            />
          </div>
        </div>
        <div className={styles.flexColumn}>
          <Button className={styles.button} variant="contained" type="submit">
            Add Restaurant
          </Button>
        </div>
      </form>
    </div>
  );
};
export default AddRestaurant;
