import { useEffect, useState } from "react";
import { DataGrid } from "@mui/x-data-grid";
import styles from "./users.module.css";
import { mobileBreakPoint } from "../../mobileBreakPoint";
import GridColumns from "./DataGrid";
import AddUser from "./AddUser";
import { fetchWrapper } from "../../../services/fetchWrapper";

interface User {
  id: number;
  email: string;
  name: string;
  surname: string;
  blacklisted: boolean;
  active: boolean;
  createdAt: string;
}

const Users: React.FC = () => {
  const [user, setUser] = useState<User[]>([]);
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
    const fetchUser = async () => {
      try {
        const userData = (await fetchWrapper.get(
          "http://localhost:8080/user/14"
        )) as User;
        setUser([userData]);
      } catch (error) {
        console.error(error);
      }
    };

    fetchUser();
  }, []);

  return (
    <div style={{ width: "100%" }}>
      <div className={isMobile ? styles.dataGridMobile : styles.dataGrid}>
        <p className={styles.title}>Employees data table</p>
        <DataGrid
          sx={{
            boxShadow: 2,
            border: 2,
            borderColor: "var(--dataGrid-color)",
            "& .MuiDataGrid-cell:hover": {
              color: "primary.main",
            },
          }}
          rows={user}
          columns={columns}
        />
      </div>
      <AddUser />
    </div>
  );
};

export default Users;
