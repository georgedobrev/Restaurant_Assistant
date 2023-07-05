import { useEffect, useState } from "react";
import AddIcon from "@mui/icons-material/Add";
import {
  TextField,
  Button,
  InputLabel,
  Select,
  MenuItem,
  FormControl,
  OutlinedInput,
  Checkbox,
  ListItemText,
} from "@mui/material";
import styles from "./sections.module.css";
import { maxHeight, storedRestaurantID } from "../../constants";
import { getAllTables, Table } from "../../../services/tablesServices";
import { getServerErrorMessage } from "../../../services/ErrorHandling";
import { createSection, SectionData } from "../../../services/sectionsService";

const AddSectionsButtons: React.FC = () => {
  const [tableNumber, setTableNumber] = useState<number[]>([]);
  const [tables, setTables] = useState<Table[]>([]);
  const [sectionName, setSectionName] = useState<string>("");
  const [errorMsg, setErrorMsg] = useState<string>("");

  const handleChange = (event: any) => {
    const {
      target: { value },
    } = event;
    setTableNumber(typeof value === "string" ? value.split(",") : value);
  };

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

  const handleAddSection = async () => {
    try {
      const sectionData: SectionData = {
        sectionName,
        appTables: tableNumber.map((tableNum) => {
          const selectedTable = tables.find(
            (table) => table.tableNumber === tableNum
          );
          return {
            id: selectedTable?.id ?? -1,
            tableNumber: tableNum,
            qr: {
              id: selectedTable?.qr.id ?? -1,
            },
            restaurant: {
              id: selectedTable?.restaurant.id ?? -1,
            },
          };
        }),
      };

      const response = await createSection(sectionData);
      return response;
    } catch (err: any) {
      setErrorMsg(getServerErrorMessage(err));
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.inputContainer}>
        <TextField
          className={styles.inputFields}
          label="Table section"
          color="warning"
          required
          value={sectionName}
          onChange={(event) => setSectionName(event.target.value)}
        />

        <FormControl sx={{ m: 1, width: 300 }}>
          <InputLabel>Tables</InputLabel>
          <Select
            color="warning"
            required
            multiple
            value={tableNumber}
            onChange={handleChange}
            input={<OutlinedInput />}
            renderValue={(selected) => selected.join(", ")}
            MenuProps={{
              PaperProps: {
                style: { maxHeight },
              },
            }}
          >
            {tables.map((table: Table) => (
              <MenuItem key={table.id} value={table.tableNumber}>
                <Checkbox
                  checked={tableNumber.indexOf(table.tableNumber) > -1}
                />
                <ListItemText primary={`Table ${table.tableNumber}`} />{" "}
              </MenuItem>
            ))}
          </Select>
        </FormControl>

        <Button className={styles.addIcon} onClick={handleAddSection}>
          <AddIcon className={styles.iconSize} style={{ fontSize: "36px" }} />
        </Button>
      </div>
    </div>
  );
};

export default AddSectionsButtons;
