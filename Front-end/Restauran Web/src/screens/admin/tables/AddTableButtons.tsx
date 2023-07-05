import { useState } from "react";
import { TextField, Button } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import { Table } from "../../../services/tablesServices";
import styles from "./tables.module.css";

type AddTableButtonsProps = {
  onAddTable: (table: Pick<Table, "tableNumber" | "capacity">) => void;
};

const AddTableButtons: React.FC<AddTableButtonsProps> = ({ onAddTable }) => {
  const [tableNumber, setTableNumber] = useState<string>("");
  const [capacity, setCapacity] = useState<string>("");

  const handleSubmit = () => {
    const num = parseInt(tableNumber);
    const cap = parseInt(capacity);
    if (isNaN(num) || isNaN(cap)) {
      alert("Invalid input");
      return;
    }

    onAddTable({ tableNumber: num, capacity: cap });
    setTableNumber("");
    setCapacity("");
  };

  return (
    <div className={styles.container}>
      <div className={styles.inputContainer}>
        <TextField
          className={styles.inputFields}
          label="Table number"
          color="warning"
          value={tableNumber}
          onChange={(e) => setTableNumber(e.target.value)}
          required
        ></TextField>
        <TextField
          className={styles.inputFields}
          label="Capacity"
          color="warning"
          value={capacity}
          onChange={(e) => setCapacity(e.target.value)}
          required
        ></TextField>
        <Button onClick={handleSubmit} className={styles.addIcon}>
          <AddIcon className={styles.iconSize} style={{ fontSize: "36px" }} />
        </Button>
      </div>
    </div>
  );
};

export default AddTableButtons;
