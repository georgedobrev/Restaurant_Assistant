import { useState } from "react";
import { Button } from "@mui/material";
import AddTenant from "./Tenant";
import styles from "./tenant.module.css"

const ButtonSection: React.FC = () => {
  const [renderComponent, setRenderComponent] = useState("addTenant");

  const handleButtonClick = (componentName: string) => {
    setRenderComponent(componentName);
  };

//   const renderSelectedComponent = () => {
//     switch (renderComponent) {
//       case "addTenant"
//         return <AddTenant />;
//       case usersSections.editUser:
//         return <EditUserComponent />;
//       case usersSections.deleteUser:
//         return <DeleteUserComponent />;
//       default:
//         return null;
//     }
//   };

  return (
    <div className={styles.container}>
      <div className={styles.buttonSection}>
        <Button
          className={styles.btns}
          variant="contained"
          onClick={() => handleButtonClick("addUser")}
        >
          Add New Tenant
        </Button>
        {/* <Button
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
        </Button> */}
      </div>

      <div>{renderComponent === "addTenant" && <AddTenant />}</div>

      {/* <div>
        {renderComponent === usersSections.editUser && <EditUserComponent />}
      </div>

      <div>
        {renderComponent === usersSections.deleteUser && (
          <DeleteUserComponent />
        )}
      </div> */}
    </div>
  );
};

export default ButtonSection;