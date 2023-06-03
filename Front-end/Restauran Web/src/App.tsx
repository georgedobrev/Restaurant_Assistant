import { Route, Routes } from "react-router-dom";
import "./App.css";
import Sidebar from "./components/sidebar/Sidebar";
import Dashboard from "./screens/Dashboard/Dashboard";
import Reports from "./screens/Reports/Reports";
import Restaurant from "./screens/Restaurant.tsx/Restaurant";
import Tables from "./screens/Tables/Tables";
import Transactions from "./screens/Transactions/Transactions";
import Users from "./screens/Users/Users";

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
