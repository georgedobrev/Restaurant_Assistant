// App.tsx
import { Route, Routes } from "react-router-dom";
import "./App.css";
import Sidebar from "./components/sidebar/Sidebar";
import Dashboard from "./screens/Dashboard";
import Reports from "./screens/Reports";
import Restaurant from "./screens/Restaurant";
import Tables from "./screens/Tables";
import Transactions from "./screens/Transactions";
import Users from "./screens/Users";

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
