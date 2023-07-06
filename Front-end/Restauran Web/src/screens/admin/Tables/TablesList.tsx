import { useState, useEffect } from "react";
import DeleteIcon from "@mui/icons-material/Delete";
import { Button } from "@mui/material";
import { getServerErrorMessage } from "../../../services/ErrorHandling";
import { storedRestaurantID } from "../../constants";
import PreviewTables from "./PreviewTables";
import styles from "./tables.module.css";
import {
  Table,
  getAllTables,
  deleteTable,
} from "../../../services/tablesServices";

export const TablesList: React.FC = () => {
  const [tables, setTables] = useState<Table[]>([]);
  const [errorMsg, setErrorMsg] = useState<string>("");

  useEffect(() => {
    const fetchTablesData = async () => {
      try {
        if (storedRestaurantID) {
          const tablesData = await getAllTables(parseInt(storedRestaurantID));
          setTables(tablesData);
        }
      } catch (err: any) {
        setErrorMsg(getServerErrorMessage(err));
      }
    };

    fetchTablesData();
  }, [storedRestaurantID]);

  const handleDeleteTable = async (tableNumber: number) => {
    try {
      await deleteTable(tableNumber);
      setTables((prevTables) =>
        prevTables.filter((table) => table.tableNumber !== tableNumber)
      );
    } catch (err: any) {
      setErrorMsg(getServerErrorMessage(err));
    }
  };

  return (
    <div className={styles.section}>
      <div className={styles.container}>
        <h1 className={styles.sectionTitle}>Existing Tables</h1>
        <div className={styles.tablesSection}>
          {tables.map((table) => (
            <div key={table.tableNumber}>
              <div className={styles.flexRow}>
                <div className={styles.notificationItem}>
                  <h4>Table: {table.tableNumber}</h4>
                  <p>Capacity: {table.capacity}</p>
                  <p>Active: {table.active ? "Yes" : "No"}</p>
                  <p>Occupied: {table.occupied ? "Yes" : "No"}</p>
                </div>
                <Button
                  className={styles.btns}
                  onClick={() => handleDeleteTable(table.tableNumber)}
                >
                  <DeleteIcon />
                </Button>
              </div>
            </div>
          ))}
        </div>
        {errorMsg && <p className={styles.errorMsg}>{errorMsg}</p>}
      </div>
      <PreviewTables />
    </div>
  );
};
