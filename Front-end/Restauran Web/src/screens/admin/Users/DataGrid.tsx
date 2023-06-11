import { Button, ThemeProvider } from "@mui/material";
import { GridCellParams, GridColDef } from "@mui/x-data-grid";
import theme from "../../../colorTheme";
import "../../../App.css";
import "./users.module.css";

interface User {
  id: number;
  email: string;
  name: string;
  surname: string;
  blacklisted: boolean;
  active: boolean;
  createdAt: string;
}

const GridColumns = (): GridColDef[] => [
  { field: "id", headerName: "ID", width: 95, editable: true },
  { field: "name", headerName: "Name", width: 150, editable: true },
  { field: "surname", headerName: "Surname", width: 150, editable: true },
  { field: "email", headerName: "Email", width: 200, editable: true },
  {
    field: "blacklisted",
    headerName: "Blacklisted",
    width: 150,
    editable: true,
    type: "boolean",
  },
  {
    field: "active",
    headerName: "Active",
    width: 150,
    editable: true,
    type: "boolean",
  },
  { field: "createdAt", headerName: "Created At", width: 150, editable: true },
  {
    field: "editRow",
    headerName: "Edit",
    width: 100,
    renderCell: (params: GridCellParams) => {
      return (
        <ThemeProvider theme={theme}>
          <Button style={{ color: "var(--brown-color)" }}>
            <i className="buttonEdit fa-solid fa-pen-to-square"></i>
          </Button>
        </ThemeProvider>
      );
    },
  },

  {
    field: "deleteRow",
    headerName: "Delete",
    width: 100,
    renderCell: (params: GridCellParams) => {
      return (
        <ThemeProvider theme={theme}>
          <Button style={{ color: theme.palette.error.main }}>
            <i className="fa-solid fa-trash"></i>
          </Button>
        </ThemeProvider>
      );
    },
  },
];

export default GridColumns;
