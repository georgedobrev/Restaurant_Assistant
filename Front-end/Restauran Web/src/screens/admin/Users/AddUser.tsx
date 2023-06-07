import { TextField } from "@mui/material";
import { Button, Box } from "@mui/material";
import MenuItem from "@mui/material/MenuItem";
import styles from "./users.module.css";

const roles = [
  {
    value: "admin",
    label: "admin",
  },
  {
    value: "waiter",
    label: "waiter",
  },
];

const AddUser = () => {
  return (
    <div className={styles.container}>
      <h2 className={styles.newUser}>Add new user</h2>

      <form>
        <div className={styles["user-details"]}>
          <div className={styles["flex-column"]}>
            <Box marginBottom={"4%"}>
              <TextField
                label="Enter Name"
                variant="outlined"
                size="small"
                color="warning"
                required
                fullWidth
              />
            </Box>

            <Box marginBottom={"4%"}>
              <TextField
                label="Enter Surname"
                variant="outlined"
                size="small"
                color="warning"
                required
                fullWidth
              />
            </Box>
          </div>

          <div className={styles["flex-column"]}>
            <Box marginBottom={"1%"}>
              <TextField
                label="Enter Email"
                variant="outlined"
                size="small"
                color="warning"
                required
                fullWidth
              />
            </Box>

            <Box
              component="form"
              sx={{
                "& .MuiTextField-root": { m: 1, width: "25ch" },
              }}
              noValidate
              autoComplete="off"
            >
              <TextField
                select
                size="small"
                label="Select"
                color="warning"
                fullWidth
                helperText="Please select role"
                style={{ width: "100%", marginLeft: 0 }}
              >
                {roles.map((option) => (
                  <MenuItem key={option.value} value={option.value}>
                    {option.label}
                  </MenuItem>
                ))}
              </TextField>
            </Box>
          </div>
        </div>
        <div className={styles["flex-column"]}>
          <Button
            variant="contained"
            style={{
              backgroundColor: "var(--primary-color)",
              color: "var(--brown-color)",
              fontWeight: "bold",
              fontSize: "12px",
              width: "46%",
              alignContent: "center",
            }}
          >
            Add User
          </Button>
        </div>
      </form>
    </div>
  );
};

export default AddUser;
