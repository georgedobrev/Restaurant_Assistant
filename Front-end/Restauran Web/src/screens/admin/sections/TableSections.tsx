import { useState, useEffect } from "react";
import DeleteIcon from "@mui/icons-material/Delete";
import { Button } from "@mui/material";
import styles from "./sections.module.css";
import AddSectionsButtons from "./AddSectionButtons";
import EditIcon from "@mui/icons-material/Edit";
import { storedRestaurantID } from "../../constants";
import {
  AppTable,
  getAllSections,
  SectionData,
} from "../../../services/sectionsService";

const TablesSection: React.FC = () => {
  const [errorMsg, setErrorMsg] = useState<string>("");
  const [sections, setSections] = useState<SectionData[]>([]);

  useEffect(() => {
    if (storedRestaurantID !== null) {
      const restaurantId: number = parseInt(storedRestaurantID);
      const fetchSections = async () => {
        try {
          const data = await getAllSections(restaurantId);
          setSections(data);
        } catch (error) {
          setErrorMsg("Failed to fetch sections data");
        }
      };
      fetchSections();
    }
  }, []);

  return (
    <div className={styles.section}>
      <div className={styles.container}>
        <h1 className={styles.sectionTitle}>Tables Sections</h1>
        <div className={styles.tablesSection}>
          {sections.map((section) => (
            <div className={styles.flexRow} key={section.sectionName}>
              <div className={styles.notificationItem}>
                <h4 className={styles.sectionNameBox}>{section.sectionName}</h4>
                <p className={styles.sectionTablesBox}>
                  Tables: {section.tableNumbers}
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
        </div>
        {errorMsg && <p className={styles.errorMsg}>{errorMsg}</p>}
      </div>
      <AddSectionsButtons />
    </div>
  );
};

export default TablesSection;
