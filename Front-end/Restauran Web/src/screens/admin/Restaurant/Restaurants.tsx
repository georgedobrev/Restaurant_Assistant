import { useEffect, useState } from "react";
import { Button } from "@mui/material";
import {
  getRestaurantsByAdminID,
  Restaurant as BaseRestaurant,
} from "../../../services/restaurantService";
import styles from "./restaurant.module.css";
import { storedUserId } from "../../constants";

interface Restaurant extends BaseRestaurant {
  isSelected?: boolean;
}

const GetRestaurants = () => {
  const [restaurants, setRestaurants] = useState<Restaurant[]>([]);
  const [selectedId, setSelectedId] = useState<number | null>(null);

  useEffect(() => {
    (async () => {
      try {
        if (storedUserId) {
          const restaurants = await getRestaurantsByAdminID(
            parseInt(storedUserId)
          );
          setRestaurants(
            restaurants.map((restaurant) => ({
              ...restaurant,
              isSelected: false,
            }))
          );
        }
      } catch (error) {
        return error;
      }
    })();
  }, []);

  useEffect(() => {
    if (selectedId) {
      localStorage.setItem("restaurantID", selectedId.toString());
    }
  }, [selectedId]);

  const handleSelect = (id: number) => {
    setSelectedId(id);
    const updatedRestaurants = restaurants.map((restaurant) => ({
      ...restaurant,
      isSelected: restaurant.id === id,
    }));
    setRestaurants(updatedRestaurants);
  };

  return (
    <div className={styles.restaurantsSection}>
      {restaurants.map((restaurant) => (
        <div
          key={restaurant.id}
          className={`${styles.restaurantContainer} ${
            restaurant.isSelected ? styles.containerSelected : ""
          }`}
        >
          <div className={styles.flexRow}>
            <div className={styles.boxName}>
              <span className={styles.restaurantName}>{restaurant.name}</span>
            </div>
            <div className={styles.boxRestaurant}>
              <span className={styles.restaurantAddress}>
                {restaurant.address}
              </span>
            </div>
          </div>
          <div>
            <Button
              className={styles.btns}
              variant="contained"
              onClick={() => restaurant.id && handleSelect(restaurant.id)}
            >
              Select
            </Button>
          </div>
        </div>
      ))}
    </div>
  );
};

export default GetRestaurants;
