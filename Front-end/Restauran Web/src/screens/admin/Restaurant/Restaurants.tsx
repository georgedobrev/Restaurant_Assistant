import { useEffect, useState } from "react";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import {
  getRestaurantsByAdminID,
  getRestaurants,
  Restaurant,
} from "../../../services/restaurantService";
import { mobileBreakPoint } from "../../mobileBreakPoint";
import styles from "./restaurant.module.css";

const GetRestaurants = () => {
  const [restaurants, setRestaurants] = useState<Restaurant[]>([]);
  const [isMobile, setIsMobile] = useState<boolean>(false);

  useEffect(() => {
    const handleResize = (): void => {
      setIsMobile(window.innerWidth <= mobileBreakPoint);
    };

    window.addEventListener("resize", handleResize);
    handleResize();

    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  const userId = localStorage.getItem("userId")

  useEffect(() => {
    (async () => {
      try {
        if (userId) {
          const restaurants = await getRestaurantsByAdminID(Number(userId));
          console.log(restaurants); 
          setRestaurants(restaurants)
        } else {
          console.log('User ID not found in local storage.');
        }
      } catch (error) {
        return error;
      }
    })();
  }, []);

  const columns: GridColDef[] = [
    { field: "id", headerName: "ID", width: 70 },
    { field: "name", headerName: "Name", width: 200 },
    { field: "tablesCount", headerName: "Tables Count", width: 150 },
    { field: "address", headerName: "Address", width: 300 },
    { field: "phoneNumber1", headerName: "Phone Number", width: 150 },
  ];

  return (
    <div className={isMobile ? styles.dataGridMobile : styles.dataGrid}>
      <div style={{ height: 400, width: "100%" }}>
        <DataGrid rows={restaurants} columns={columns} />
      </div>
    </div>
  );
};

export default GetRestaurants;
