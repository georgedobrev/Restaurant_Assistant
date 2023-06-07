import { Route, Routes } from "react-router-dom";
import "./App.css";
import Sidebar from "./components/sidebar/SideBar";
import Dashboard from "./screens/admin/Dashboard/Dashboard";
import Reports from "./screens/admin/Reports/Reports";
import Restaurant from "./screens/admin/Restaurant.tsx/Restaurant";
import Tables from "./screens/admin/Tables/Tables";
import Transactions from "./screens/admin/Transactions/Transactions";
import Users from "./screens/admin/Users/Users";

function App() {
  return (
    <div>
      <Sidebar />

      <Routes>
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/users" element={<Users />} />
        <Route path="/restaurant" element={<Restaurant />} />
        <Route path="/tables" element={<Tables />} />
        <Route path="/reports" element={<Reports />} />
        <Route path="/transactions" element={<Transactions />} />
      </Routes>
    </div>
  );
}

export default App;
