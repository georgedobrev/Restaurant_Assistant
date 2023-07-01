import { useState } from "react";
import { Button } from "@mui/material";
import AddUser from "./AddAdmin";
import EditUserComponent from "./EditEmployee";
import DeleteUserComponent from "./DeleteEmployee";
import styles from "./employees.module.css";
import { usersSections } from "../../constants";
import AddRoles from "./AddWaiter";

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
      case usersSections.addUserRole:
        return <AddRoles />;
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
          Add Admin
        </Button>
        <Button
          className={styles.btns}
          variant="contained"
          onClick={() => handleButtonClick(usersSections.addUserRole)}
        >
          Add Waiter
        </Button>
        <Button
          className={styles.btns}
          variant="contained"
          onClick={() => handleButtonClick(usersSections.editUser)}
        >
          Edit Employee
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

      <div>{renderComponent === usersSections.addUserRole && <AddRoles />}</div>
    </div>
  );
};

export default ButtonSection;
