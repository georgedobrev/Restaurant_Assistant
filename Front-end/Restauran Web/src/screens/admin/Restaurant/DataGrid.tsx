import { Button, ThemeProvider } from "@mui/material";
import { GridCellParams, GridColDef } from "@mui/x-data-grid";
import theme from "../../../colorTheme";

interface RestaurantData {
  id: number;
  name: string;
  tablesCount: number;
  address: string;
  email: string;
  phoneNumber1: string;
  phoneNumber2: string;
}

const GridColumns = (): GridColDef[] => [
  { field: "id", headerName: "ID", width: 95, editable: true },
  { field: "name", headerName: "Restaurant", width: 150, editable: true },
  { field: "tablesCount", headerName: "Tables", width: 100, editable: true },
  { field: "address", headerName: "Address", width: 200, editable: true },
  {
    field: "phoneNumber1",
    headerName: "Phone 1",
    width: 120,
    editable: true,
  },
  {
    field: "phoneNumber2",
    headerName: "Phone 2",
    width: 120,
    editable: true,
  },
  {
    field: "editRow",
    headerName: "Edit",
    width: 100,
    renderCell: (params: GridCellParams) => {
      return (
        <ThemeProvider theme={theme}>
          <Button className="" style={{ color: "var(--brown-color)" }}>
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
          <Button
            className="button-add-user"
            style={{ color: theme.palette.error.main }}
          >
            <i className="fa-solid fa-trash"></i>
          </Button>
        </ThemeProvider>
      );
    },
  },
];

export default GridColumns;
