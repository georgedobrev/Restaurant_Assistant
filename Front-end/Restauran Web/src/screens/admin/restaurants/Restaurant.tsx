import ButtonSection from "./ButtonSection";
import styles from "./restaurant.module.css";

const RestaurantComponent: React.FC = () => {
  return (
    <div className={styles.section}>
      <ButtonSection />
    </div>
  );
};

export default RestaurantComponent;
