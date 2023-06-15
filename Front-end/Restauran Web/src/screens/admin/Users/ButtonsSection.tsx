import { useState } from "react";
import { Button } from "@mui/material";
import AddUser from "./AddUser";
import EditUserComponent from "./EditUser";
import DeleteUserComponent from "./DeleteComponent";
import styles from "./users.module.css";

const ButtonSection = () => {
  const [renderComponent, setRenderComponent] = useState("addUser");

  const handleButtonClick = (componentName: string) => {
    setRenderComponent(componentName);
  };

  const renderSelectedComponent = () => {
    switch (renderComponent) {
      case "addUser":
        return <AddUser />;
      case "editUser":
        return <EditUserComponent />;
      case "deleteUser":
        return <DeleteUserComponent />;
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
          onClick={() => handleButtonClick("addUser")}
        >
          Add New User
        </Button>
        <Button
          className={styles.btns}
          variant="contained"
          onClick={() => handleButtonClick("editUser")}
        >
          Edit User
        </Button>
        <Button
          className={styles.btns}
          variant="contained"
          onClick={() => handleButtonClick("deleteUser")}
        >
          Delete User
        </Button>
      </div>

      <div>{renderComponent === "addUser" && <AddUser />}</div>

      <div>{renderComponent === "editUser" && <EditUserComponent />}</div>

      <div>{renderComponent === "deleteUser" && <DeleteUserComponent />}</div>
    </div>
  );
};

export default ButtonSection;
