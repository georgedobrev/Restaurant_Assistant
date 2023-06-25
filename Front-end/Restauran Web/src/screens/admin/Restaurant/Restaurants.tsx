import { useEffect, useState } from "react";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import {
  getRestaurantsByAdminID,
  Restaurant,
} from "../../../services/restaurantService";
import { mobileBreakPoint } from "../../constants";
import styles from "./restaurant.module.css";
import { storedUserId } from "../../constants";

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

  useEffect(() => {
    (async () => {
      try {
        if (storedUserId) {
          const restaurants = await getRestaurantsByAdminID(
            parseInt(storedUserId)
          );
          setRestaurants(restaurants);
        }
      } catch (error) {
        return error;
      }
    })();
  }, []);

  const columns: GridColDef[] = [
    { field: "id", headerName: "ID", width: 50 },
    { field: "name", headerName: "Name", width: 100 },
    { field: "tablesCount", headerName: "Tables Count", width: 120 },
    { field: "address", headerName: "Address", width: 200 },
    { field: "phoneNumber1", headerName: "Phone Number", width: 150 },
  ];

  return (
    <div className={isMobile ? styles.dataGridMobile : styles.dataGrid}>
      <div style={{ height: 400, width: "85%" }}>
        <DataGrid rows={restaurants} columns={columns} />
      </div>
    </div>
  );
};

export default GetRestaurants;
