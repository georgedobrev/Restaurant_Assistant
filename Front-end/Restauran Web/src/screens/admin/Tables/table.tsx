// import { useState } from "react";
// import { Button, TextField } from "@mui/material";
// import styles from "./tenant.module.css";
// import { createTable } from "../../../services/tablesService";

// const AddTenant: React.FC = () => {
//   const [name, setName] = useState<string>("");
//   const [id, setId] = useState<number>(0); // Change the initial value accordingly
//   const [email, setEmail] = useState<string>("");
//   const [surname, setSurname] = useState<string>("");

//   const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
//     event.preventDefault();

//     const tenantData = {
//       name: name,
//       surname: surname,
//       email: email,
//       restaurant: {
//         id: 1, 
//       },
//     };

//     try {
//       const response: Tenant = await createTenant(tenantData);
//       setName("");
//       setSurname("");
//       setEmail("");
//       return response;
//     } catch (error) {
//       return error;
//     }
//   };

//   return (
//     <div>
//       <h2 className={styles.newUser}>Add new tenant</h2>

//       <form className={styles.submitForm} onSubmit={handleSubmit}>
//         <TextField
//           label="Name"
//           value={name}
//           onChange={(e) => setName(e.target.value)}
//           color="warning"
//           required
//           margin="normal"
//           className={styles.inputFields}
//         />

//         <TextField
//           label="Surname"
//           value={surname}
//           onChange={(e) => setSurname(e.target.value)}
//           required
//           color="warning"
//           margin="normal"
//           className={styles.inputFields}
//         />

//         <TextField
//           label="Email"
//           value={email}
//           onChange={(e) => setEmail(e.target.value)}
//           required
//           color="warning"
//           margin="normal"
//           className={styles.inputFields}
//         />

//         <TextField
//           label="Restaurant"
//           value={id}
//           onChange={(e) => setId(e.target.value)}
//           required
//           color="warning"
//           margin="normal"
//           className={styles.inputFields}
//         />

//         <Button type="submit" className={styles.btn} variant="contained">
//           Add new tenant
//         </Button>
//       </form>
//     </div>
//   );
// };

// export default AddTenant;
