import { TextField, Box } from "@mui/material";

const RestaurantInputFields: React.FC<{
  label: string;
  value: string;
  setValue: (value: string) => void;
}> = ({ label, value, setValue }) => {
  return (
    <Box marginBottom={"4%"} marginRight={"16%"}>
      <TextField
        onChange={(e) => setValue(e.target.value)}
        label={label}
        variant="outlined"
        size="small"
        color="warning"
        required
        fullWidth
        value={value}
      />
    </Box>
  );
};

export default RestaurantInputFields;
