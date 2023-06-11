import { TextField, Box } from "@mui/material";
import styles from "../users/users.module.css";

interface UserInputFieldsProps {
  value: string;
  onChange: (value: string) => void;
  label: string;
  fullWidth?: boolean;
  required?: boolean;
  marginBottom?: string;
}

const UserInputFields: React.FC<UserInputFieldsProps> = ({
  value,
  onChange,
  label,
  fullWidth,
  required,
  marginBottom,
}) => {
  return (
    <Box marginBottom={marginBottom}>
      <TextField
        className={styles.inputFields}
        label={label}
        variant="outlined"
        size="small"
        color="warning"
        required={required}
        fullWidth={fullWidth}
        value={value}
        onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
          onChange(event.target.value);
        }}
      />
    </Box>
  );
};

export default UserInputFields;
