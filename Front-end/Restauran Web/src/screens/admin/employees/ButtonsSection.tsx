import { useState } from "react";
import { Button } from "@mui/material";
import AddEmployee from "./AddEmployee";
import EditUserComponent from "./EditEmployee";
import DeleteUserComponent from "./DeleteEmployee";
import styles from "./employees.module.css";
import { usersSections } from "../../constants";

const ButtonSection: React.FC = () => {
  const [renderComponent, setRenderComponent] = useState("addUser");

  const handleButtonClick = (componentName: string) => {
    setRenderComponent(componentName);
  };

  const renderSelectedComponent = () => {
    switch (renderComponent) {
      case usersSections.addUser:
        return <AddEmployee />;
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
          Add Employee
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
          Delete Employee
        </Button>
      </div>

      <div>{renderComponent === usersSections.addUser && <AddEmployee />}</div>

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
