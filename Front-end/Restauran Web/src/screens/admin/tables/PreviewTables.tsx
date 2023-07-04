import { useState } from "react";
import { Button } from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit";
import { createTables, Table } from "../../../services/tablesServices";
import styles from "./tables.module.css";
import AddTables from "./AddTableButtons";
import { getServerErrorMessage } from "../../../services/ErrorHandling";

type TableData = Pick<Table, "tableNumber" | "capacity">;

const PreviewTables: React.FC = () => {
  const [newTables, setNewTables] = useState<TableData[]>([]);
  const [errorMsg, setErrorMsg] = useState<string>("");

  const handleAddTable = (table: TableData) => {
    setNewTables((prevTables) => [...prevTables, table]);
  };

  const handleSaveTables = async () => {
    try {
      await createTables(newTables);
      setNewTables([]);
    } catch (err: any) {
      setErrorMsg(getServerErrorMessage(err));
    }
  };

  return (
    <div>
      <div className={styles.container}>
        <h2 className={styles.sectionTitle}>Newly added tables</h2>
        {newTables.length > 0 ? (
          <div className={styles.tablesSection}>
            {newTables.map((table, index) => (
              <div key={index} className={styles.flexRow}>
                <div className={styles.notificationItem}>
                  <h4>Table: {table.tableNumber}</h4>
                  <p>
                    Create new table No: {table.tableNumber} with capacity{" "}
                    {table.capacity}
                  </p>
                </div>
                <Button className={styles.btnEdit}>
                  <EditIcon />
                </Button>
                <Button className={styles.btns}>
                  <DeleteIcon />
                </Button>
              </div>
            ))}
            <Button className={styles.btnSave} onClick={handleSaveTables}>
              Save
            </Button>
          </div>
        ) : (
          <div className={styles.tablesSection}>
            <p className={styles.noTables}>
              No new tables have been added yet.
            </p>
          </div>
        )}
      </div>
      {errorMsg && <p className={styles.errorMsg}>{errorMsg}</p>}
      <AddTables onAddTable={handleAddTable} />
    </div>
  );
};

export default PreviewTables;
