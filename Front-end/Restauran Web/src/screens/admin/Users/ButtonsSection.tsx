import { useState } from "react";
import { Button } from "@mui/material";
import AddUser from "./AddUser";
import EditUserComponent from "./EditUser";
import DeleteUserComponent from "./DeleteComponent";
import styles from "./users.module.css";
import { usersSections } from "../../constants";

const ButtonSection: React.FC = () => {
  const [renderComponent, setRenderComponent] = useState("addUser");

  const handleButtonClick = (componentName: string) => {
    setRenderComponent(componentName);
  };

  const renderSelectedComponent = () => {
    switch (renderComponent) {
      case usersSections.addUser:
        return <AddUser />;
      case usersSections.editUser:
        return <EditUserComponent />;
      case usersSections.deleteUser:
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
          onClick={() => handleButtonClick(usersSections.addUser)}
        >
          Add New User
        </Button>
        <Button
          className={styles.btns}
          variant="contained"
          onClick={() => handleButtonClick(usersSections.editUser)}
        >
          Edit User
        </Button>
        <Button
          className={styles.btns}
          variant="contained"
          onClick={() => handleButtonClick(usersSections.deleteUser)}
        >
          Delete User
        </Button>
      </div>

      <div>{renderComponent === usersSections.addUser && <AddUser />}</div>

      <div>
        {renderComponent === usersSections.editUser && <EditUserComponent />}
      </div>

      <div>
        {renderComponent === usersSections.deleteUser && (
          <DeleteUserComponent />
        )}
      </div>
    </div>
  );
};

export default ButtonSection;
