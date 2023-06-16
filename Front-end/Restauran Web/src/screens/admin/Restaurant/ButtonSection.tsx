import { useState } from "react";
import { Button } from "@mui/material";
import AddRestaurant from "./AddRestaurant";
import styles from "./restaurant.module.css";
import EditRestaurantComponent from "./EditRestaurant";
import GetRestaurants from "./GetRestaurants";
import { RestaurantSections } from "../../constants";

const ButtonSection: React.FC = () => {
  const [renderComponent, setRenderComponent] = useState("addRestaurant");

  const handleButtonClick = (componentName: string) => {
    setRenderComponent(componentName);
  };

  const renderSelectedComponent = () => {
    switch (renderComponent) {
      case RestaurantSections.addRestaurant:
        return <AddRestaurant />;
      case RestaurantSections.editRestaurant:
        return <EditRestaurantComponent />;
      case RestaurantSections.getAllRestaurants:
        return <GetRestaurants />;
      default:
        return null;
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.buttonSection}>
        <Button
          className={styles.btns}
          variant="contained"
          onClick={() => handleButtonClick(RestaurantSections.addRestaurant)}
        >
          Add Restaurant
        </Button>
        <Button
          className={styles.btns}
          variant="contained"
          onClick={() => handleButtonClick(RestaurantSections.editRestaurant)}
        >
          Edit Restaurant
        </Button>
        <Button
          className={styles.btns}
          variant="contained"
          onClick={() => handleButtonClick(RestaurantSections.getAllRestaurants)}
        >
          All Restaurants
        </Button>
      </div>

      <div>{renderComponent === RestaurantSections.addRestaurant && <AddRestaurant />}</div>

      <div>
        {renderComponent === RestaurantSections.editRestaurant && <EditRestaurantComponent />}
      </div>

      <div>{renderComponent === RestaurantSections.getAllRestaurants && <GetRestaurants />}</div>
    </div>
  );
};

export default ButtonSection;
