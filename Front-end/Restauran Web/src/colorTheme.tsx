import { createTheme } from "@mui/material/styles";
import { red } from "@mui/material/colors";

const theme = createTheme({
  palette: {
    primary: {
      main: "#ffd54f", // main yellow color
      contrastText: "#fff",
    },
    secondary: {
      main: "#ff9800", // orange color
      dark: "#5d4037", // brown color
      contrastText: "#000",
    },
    error: {
      main: red[500], // red color for delete button and errors
    },
  },
});

export default theme;
