import { useState } from "react";
import { Button } from "@mui/material";
import AddRestaurant from "./AddRestaurant";
import styles from "./restaurant.module.css";
import EditRestaurantComponent from "./EditRestaurant";
import GetRestaurants from "./GetRestaurants";

const ButtonSection = () => {
  const [renderComponent, setRenderComponent] = useState("addRestaurant");

  const handleButtonClick = (componentName: any) => {
    setRenderComponent(componentName);
  };

  const renderSelectedComponent = () => {
    switch (renderComponent) {
      case "addUser":
        return <AddRestaurant />;
      case "editUser":
        return <EditRestaurantComponent />;
      case "deleteUser":
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
          onClick={() => handleButtonClick("addRestaurant")}
        >
          Add Restaurant
        </Button>
        <Button
          className={styles.btns}
          variant="contained"
          onClick={() => handleButtonClick("editRestaurant")}
        >
          Edit Restaurant
        </Button>
        <Button
          className={styles.btns}
          variant="contained"
          onClick={() => handleButtonClick("allRestaurants")}
        >
          All Restaurants
        </Button>
      </div>

      <div>{renderComponent === "addRestaurant" && <AddRestaurant />}</div>

      <div>
        {renderComponent === "editRestaurant" && <EditRestaurantComponent />}
      </div>

      <div>{renderComponent === "allRestaurants" && <GetRestaurants />}</div>
    </div>
  );
};

export default ButtonSection;
