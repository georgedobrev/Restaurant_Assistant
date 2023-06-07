import { GridColDef } from "@mui/x-data-grid";
import { Button, ThemeProvider } from "@mui/material";
import theme from "../../../colorTheme";

const gridColumns = (
  onButtonClick: (e: React.MouseEvent<HTMLButtonElement>, row: any) => void
): GridColDef[] => [
  { field: "id", headerName: "ID", width: 95, editable: true },
  { field: "name", headerName: "Restaurant", width: 150, editable: true },
  { field: "tables_count", headerName: "Tables", width: 100, editable: true },
  { field: "address", headerName: "Address", width: 200, editable: true },
  { field: "email", headerName: "E-mail Address", width: 200, editable: true },
  {
    field: "phone_number_1",
    headerName: "Phone 1",
    width: 200,
    editable: true,
  },
  {
    field: "phone_number_2",
    headerName: "Phone 2",
    width: 200,
    editable: true,
  },
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
