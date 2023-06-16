import { useState } from "react";
import { Button } from "@mui/material";
import AddUser from "./AddUser";
import EditUserComponent from "./EditUser";
import DeleteUserComponent from "./DeleteComponent";
import styles from "./users.module.css";
import { UsersSections } from "../../constants";

const ButtonSection: React.FC = () => {
  const [renderComponent, setRenderComponent] = useState("addUser");

  const handleButtonClick = (componentName: string) => {
    setRenderComponent(componentName);
  };

  const renderSelectedComponent = () => {
    switch (renderComponent) {
      case UsersSections.addUser:
        return <AddUser />;
      case UsersSections.editUser:
        return <EditUserComponent />;
      case UsersSections.deleteUser:
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
          onClick={() => handleButtonClick(UsersSections.addUser)}
        >
          Add New User
        </Button>
        <Button
          className={styles.btns}
          variant="contained"
          onClick={() => handleButtonClick(UsersSections.editUser)}
        >
          Edit User
        </Button>
        <Button
          className={styles.btns}
          variant="contained"
          onClick={() => handleButtonClick(UsersSections.deleteUser)}
        >
          Delete User
        </Button>
      </div>

      <div>{renderComponent === UsersSections.addUser && <AddUser />}</div>

      <div>{renderComponent === UsersSections.editUser && <EditUserComponent />}</div>

      <div>{renderComponent === UsersSections.deleteUser && <DeleteUserComponent />}</div>
    </div>
  );
};

export default ButtonSection;
