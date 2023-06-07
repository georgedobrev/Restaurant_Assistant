// gridColumns.ts
import { GridColDef } from "@mui/x-data-grid";
import { Button, ThemeProvider } from "@mui/material";
import theme from "../../../colorTheme";
import "../../../App.css";
import "./users.module.css";

const gridColumns = (
  onButtonClick: (e: React.MouseEvent<HTMLButtonElement>, row: any) => void
): GridColDef[] => [
  { field: "id", headerName: "ID", width: 95, editable: true },
  { field: "firstName", headerName: "Name", width: 150, editable: true },
  { field: "lastName", headerName: "Surname", width: 150, editable: true },
  { field: "role", headerName: "Role", width: 90, editable: true },
  { field: "email", headerName: "Email", width: 200, editable: true },
  {
    field: "editRow",
    headerName: "Edit",
    width: 100,
    renderCell: (params) => {
      return (
        <ThemeProvider theme={theme}>
          <Button
            className=""
            onClick={(e) => onButtonClick(e, params.row)}
            style={{ color: "var(--brown-color)" }}
          >
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
    renderCell: (params) => {
      return (
        <ThemeProvider theme={theme}>
          <Button
            className="button-add-user"
            onClick={(e) => onButtonClick(e, params.row)}
            style={{ color: theme.palette.error.main }}
          >
            <i className="fa-solid fa-trash"></i>
          </Button>
        </ThemeProvider>
      );
    },
  },
];

export default gridColumns;
