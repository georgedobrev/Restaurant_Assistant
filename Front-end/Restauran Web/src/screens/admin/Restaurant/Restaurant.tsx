import { useEffect, useState } from "react";
import { DataGrid } from "@mui/x-data-grid";
import GridColumns from "./DataGrid";
import styles from "./restaurant.module.css";
import { mobileBreakPoint } from "../../mobileBreakPoint";
import { getRestaurants } from "../../../services/restaurantService";
import AddRestaurant from "./AddRestaurant";

interface Restaurant {
  id: number;
  name: string;
  tables_count: string;
  address: string;
  phone_number_1: string;
  phone_number_2: string;
}

const RestaurantComponent: React.FC = () => {
  const [restaurants, setRestaurants] = useState<Restaurant[]>([]);
  const [isMobile, setIsMobile] = useState(false);

  const columns = GridColumns();

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
    const fetchRestaurants = async () => {
      try {
        const data = (await getRestaurants()) as Restaurant[];
        setRestaurants(data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchRestaurants();
  }, []);

  return (
    <div>
      <div className={isMobile ? styles.dataGridMobile : styles.dataGrid}>
        <p className={styles.title}>Restaurants data table</p>
        <DataGrid
          sx={{
            boxShadow: 2,
            border: 2,
            borderColor: "var(--dataGrid-color)",
            "& .MuiDataGrid-cell:hover": {
              color: "primary.main",
            },
          }}
          rows={restaurants}
          columns={columns}
        />
      </div>
      <AddRestaurant />
    </div>
  );
};

export default RestaurantComponent;
