import { Link } from "react-router-dom";

const Navigation = () => (
  <nav className="nav">
    <div>
      <Link to="/" className="nav-logo">
        <i className={`fas fa-home-alt nav-logo-icon`}></i>
        <span className="nav-logo-name">Homepage</span>
      </Link>

      <div className="nav-list">
        <Link to="/dashboard" className="nav-link">
          <i className="fas fa-tachometer-alt nav-link-icon"></i>
          <span className="nav-link-name">Dashboard</span>
        </Link>

        <Link to="/users" className="nav-link">
          <i className="fa-solid fa-user"></i>
          <span className="nav-link-name">Users</span>
        </Link>

        <Link to="/restaurant" className="nav-link">
          <i className="fa-solid fa-utensils"></i>
          <span className="nav-link-name">Restaurant</span>
        </Link>

        <Link to="/tables" className="nav-link">
          <i className="fa-solid fa-table"></i>
          <span className="nav-link-name">Tables</span>
        </Link>

        <Link to="/reports" className="nav-link">
          <i className="fa-solid fa-file-lines"></i>
          <span className="nav-link-name">Reports</span>
        </Link>

        <Link to="/transactions" className="nav-link">
          <i className="fas fa-dollar-sign nav-link-icon"></i>
          <span className="nav-link-name">Transaction</span>
        </Link>
      </div>
    </div>

    <Link to="/logout" className="nav-link">
      <i className="fas fa-sign-out nav-link-icon"></i>
      <span className="nav-link-name">Logout</span>
    </Link>
  </nav>
);

export default Navigation;