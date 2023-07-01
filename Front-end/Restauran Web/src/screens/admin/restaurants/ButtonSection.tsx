import { useState } from "react";
import { Button } from "@mui/material";
import AddRestaurant from "./AddRestaurant";
import styles from "./restaurant.module.css";
import EditRestaurantComponent from "./EditRestaurant";
import GetRestaurants from "./DisplayRestaurants";
import { restaurantSections } from "../../constants";

const ButtonSection: React.FC = () => {
  const [renderComponent, setRenderComponent] = useState("");

  const handleButtonClick = (componentName: string) => {
    setRenderComponent(componentName);
  };

  const renderSelectedComponent = () => {
    switch (renderComponent) {
      case restaurantSections.addRestaurant:
        return <AddRestaurant />;
      case restaurantSections.editRestaurant:
        return <EditRestaurantComponent />;
      default:
        return null;
    }
  };

  return (
    <div className={styles.container}>
      <GetRestaurants />
      <div className={styles.buttonSection}>
        <Button
          className={styles.btns}
          variant="contained"
          onClick={() => handleButtonClick(restaurantSections.addRestaurant)}
        >
          Add Restaurant
        </Button>
        <Button
          className={styles.btns}
          variant="contained"
          onClick={() => handleButtonClick(restaurantSections.editRestaurant)}
        >
          Edit Restaurant
        </Button>
      </div>

      <div>
        {renderComponent === restaurantSections.addRestaurant && (
          <AddRestaurant />
        )}
      </div>

      <div>
        {renderComponent === restaurantSections.editRestaurant && (
          <EditRestaurantComponent />
        )}
      </div>
    </div>
  );
};

export default ButtonSection;
