import { Button, Box, TextField } from "@mui/material";
import MenuItem from "@mui/material/MenuItem";
import styles from "./users.module.css";
import { Role, roles } from "./rolesData";

const AddUser = () => {
  return (
    <div className={styles.container}>
      <h2 className={styles.newUser}>Add new user</h2>

      <form>
        <div className={styles.userDetails}>
          <div className={styles.flexColumn}>
            <Box marginBottom={"4%"}>
              <TextField
                className={styles.inputFields}
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
                className={styles.inputFields}
                label="Enter Surname"
                variant="outlined"
                size="small"
                color="warning"
                required
                fullWidth
              />
            </Box>
          </div>

          <div className={styles.flexColumn}>
            <Box marginBottom={"1%"}>
              <TextField
                className={styles.inputFields}
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
                className={styles.inputFields}
                select
                size="small"
                label="Select"
                color="warning"
                fullWidth
                helperText="Please select role"
                style={{ width: "100%", marginLeft: 0 }}
              >
                {roles.map((option: Role) => (
                  <MenuItem key={option.value} value={option.value}>
                    {option.label}
                  </MenuItem>
                ))}
              </TextField>
            </Box>
          </div>
        </div>

        <div className={styles.flexColumn}>
          <Button
            variant="contained"
            className={styles.button}
            style={{ background: "var(--primary-color)" }}
          >
            Add User
          </Button>
        </div>
      </form>
    </div>
  );
};

export default AddUser;
