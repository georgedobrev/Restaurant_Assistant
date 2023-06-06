import { useState } from "react";
import styles from "./sidebar.module.css";
import "../../App.css";
import Navigation from "./Navigation";
import theme from "../../colorTheme";

const Sidebar: React.FC = () => {
  const [show, setShow] = useState<boolean>(false);

  return (
    <main className={styles.main}>
      <header
        style={{ color: theme.palette.primary.main }}
        className={`${styles.header} ${show ? styles["space-toggle"] : ""}`}
      >
        <div className={styles["header-toggle"]} onClick={() => setShow(!show)}>
          <i className={`fas fa-bars ${show ? "fa-solid fa-xmark" : ""}`}></i>
        </div>
      </header>

      <aside
        style={{ background: theme.palette.primary.main }}
        className={`${styles.sidebar} ${show ? styles.show : ""}`}
      >
        <Navigation />
      </aside>
    </main>
  );
};

export default Sidebar;
