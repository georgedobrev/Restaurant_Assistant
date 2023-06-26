import { Route, Routes } from "react-router-dom";
import { useState } from "react";
import "./App.css";
import Sidebar from "./components/sidebar/SideBar";
import Dashboard from "./screens/admin/Dashboard/Dashboard";
import Reports from "./screens/admin/Reports/Reports";
import Restaurant from "./screens/admin/Restaurant/Restaurant";
import Tables from "./screens/admin/Tables/Tables";
import Transactions from "./screens/admin/Transactions/Transactions";
import Users from "./screens/admin/Users/Users";
import Customer from "./screens/customer/Customer";
import LoginScreen from "./screens/login/LoginScreen";

function App() {
  const [loggedIn, setLoggedIn] = useState(false);
  const [userType, setUserType] = useState("");

  const handleLoggedIn = (loggedIn: boolean) => {
    setLoggedIn(loggedIn);
  };

  const handleUserType = (userType: string) => {
    setUserType(userType);
  };

  if (loggedIn) {
    if (userType === "user") {
      return (
        <div>
          <Customer appUserId={1} appTableId={1} />
        </div>
      );
    } else {
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
  } else {
    return (
      <LoginScreen setLoggedIn={handleLoggedIn} setUserType={handleUserType} />
    );
  }
}

export default App;
