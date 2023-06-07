import { useEffect, useState } from "react";
import { DataGrid } from "@mui/x-data-grid";
import gridCol from "./DataGrid";
import rows from "./data";
import AddRestaurant from "./AddRestaurant";
import styles from "./restaurant.module.css";

interface User {
  id: number;
  name: string;
  tables_count: string;
  address: string;
  phone_number_1: string;
}

const Restaurant = (): JSX.Element => {
  const [clickedRow, setClickedRow] = useState<User | undefined>();

  const onButtonClick = (
    e: React.MouseEvent<HTMLButtonElement>,
    row: User
  ): void => {
    e.stopPropagation();
    setClickedRow(row);
  };

  const columns = gridCol(onButtonClick);

  const [isMobile, setIsMobile] = useState(false);

  useEffect(() => {
    const handleResize = (): void => {
      setIsMobile(window.innerWidth <= 768);
    };

    window.addEventListener("resize", handleResize);
    handleResize();

    return () => {
      window.removeEventListener("resize", handleResize);
    };
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
          rows={rows}
          columns={columns}
        />
      </div>
      <AddRestaurant />
    </div>
  );
};

export default Restaurant;
