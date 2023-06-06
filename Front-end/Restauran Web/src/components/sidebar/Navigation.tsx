import { Link } from "react-router-dom";
import styles from "./sidebar.module.css"; // Assuming this is the location of your CSS module

const Navigation = () => (
  <nav className={styles.nav}>
    <div>
      <Link to="/" className={styles["nav-logo"]}>
        <i className={`fas fa-home-alt ${styles["nav-logo-icon"]}`}></i>
        <span className={styles["nav-logo-name"]}>Homepage</span>
      </Link>

      <div className={styles["nav-list"]}>
        <Link to="/dashboard" className={styles["nav-link"]}>
          <i className={`fas fa-tachometer-alt ${styles["nav-link-icon"]}`}></i>
          <span className={styles["nav-link-name"]}>Dashboard</span>
        </Link>

        <Link to="/users" className={styles["nav-link"]}>
          <i className="fa-solid fa-user"></i>
          <span className={styles["nav-link-name"]}>Users</span>
        </Link>

        <Link to="/restaurant" className={styles["nav-link"]}>
          <i className="fa-solid fa-utensils"></i>
          <span className={styles["nav-link-name"]}>Restaurant</span>
        </Link>

        <Link to="/tables" className={styles["nav-link"]}>
          <i className="fa-solid fa-table"></i>
          <span className={styles["nav-link-name"]}>Tables</span>
        </Link>

        <Link to="/reports" className={styles["nav-link"]}>
          <i className="fa-solid fa-file-lines"></i>
          <span className={styles["nav-link-name"]}>Reports</span>
        </Link>

        <Link to="/transactions" className={styles["nav-link"]}>
          <i className={`fas fa-dollar-sign ${styles["nav-link-icon"]}`}></i>
          <span className={styles["nav-link-name"]}>Transaction</span>
        </Link>
      </div>
    </div>

    <Link to="/logout" className={styles["nav-link"]}>
      <i className={`fas fa-sign-out ${styles["nav-link-icon"]}`}></i>
      <span className={styles["nav-link-name"]}>Logout</span>
    </Link>
  </nav>
);

export default Navigation;
