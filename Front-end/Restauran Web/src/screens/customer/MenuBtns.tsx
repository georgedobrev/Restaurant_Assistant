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
        style={{
          content: '""',
          position: "absolute",
          left: 0,
          right: 0,
          bottom: -2,
          height: 2,
          backgroundColor: "white",
        }}
      ></span>
    </Button>
  );
};

export default MenuBtns;