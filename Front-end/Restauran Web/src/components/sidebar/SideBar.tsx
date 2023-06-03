import { useState } from "react";
import "./sidebar.css";
import "../../App.css";
import Navigation from "./Navigation";
import theme from "../../colorTheme";

const Sidebar: React.FC = () => {
  const [show, setShow] = useState<boolean>(false);

  return (
    <main className={show ? "space-toggle" : ""}>
      <header className={`header ${show ? "space-toggle" : ""}`}>
        <div className="header-toggle" onClick={() => setShow(!show)}>
          <i
            className={`fas fa-bars ${show ? "fa-solid fa-xmark" : ""}`}
            style={{ color: theme.palette.primary.main }}
          ></i>
        </div>
      </header>

      <aside
        style={{ backgroundColor: theme.palette.primary.main }}
        className={`primary sidebar ${show ? "show" : ""}`}
      >
        <Navigation />
      </aside>
    </main>
  );
};

export default Sidebar;
