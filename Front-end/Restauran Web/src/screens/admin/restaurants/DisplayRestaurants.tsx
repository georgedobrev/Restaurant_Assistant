import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "@mui/material";
import styles from "./restaurant.module.css";
import { storedUserId } from "../../constants";
import {
  getRestaurantsByAdminID,
  Restaurant as BaseRestaurant,
} from "../../../services/restaurantService";

interface Restaurant extends BaseRestaurant {
  isSelected?: boolean;
}

const DisplayRestaurants: React.FC = () => {
  const [restaurants, setRestaurants] = useState<Restaurant[]>([]);
  const [selectedId, setSelectedId] = useState<number | null>(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchRestaurants = async () => {
      try {
        if (storedUserId) {
          const restaurantsData = await getRestaurantsByAdminID(
            parseInt(storedUserId)
          );
          const updatedRestaurants = restaurantsData.map((restaurant) => ({
            ...restaurant,
            isSelected: false,
          }));
          setRestaurants(updatedRestaurants);
        }
      } catch (error) {
        console.error(error);
      }
    };

    fetchRestaurants();
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

  const handleNavigate = () => {
    const isRestaurantSelected = restaurants.some(
      (restaurant) => restaurant.isSelected
    );

    isRestaurantSelected
      ? navigate("/tables")
      : alert("Please select a restaurant");
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
      <Button className={styles.manageBtn} onClick={() => handleNavigate()}>
        Manage Restaurant
      </Button>
    </div>
  );
};

export default DisplayRestaurants;
