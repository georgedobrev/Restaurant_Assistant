import { Button, ButtonProps } from "@mui/material";
import styles from "./customer.module.css";

interface MenuBtnsProps extends ButtonProps {
  onClick: () => void;
}

const MenuBtns: React.FC<MenuBtnsProps> = ({ onClick, children, ...props }) => {
  return (
    <Button className={styles.buttons} onClick={onClick} {...props}>
      {children}
      <span
     className={styles.span}
      ></span>
    </Button>
  );
};

export default MenuBtns;