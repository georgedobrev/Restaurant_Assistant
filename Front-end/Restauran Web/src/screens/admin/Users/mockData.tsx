interface rowsRestaurant {
  id: number;
  firstName: string;
  lastName: string;
  role: string;
  email: string;
}

const rows: rowsRestaurant[] = [
  {
    id: 1,
    firstName: "Martin",
    lastName: "Kanev",
    role: "admin",
    email: "marto.kanev@gmail.com",
  },
  {
    id: 4,
    firstName: "Hristyana",
    lastName: "Shopova",
    role: "waiter",
    email: "hrisi.shopova@email.com",
  },
  {
    id: 2,
    firstName: "Dame",
    lastName: "Stoilovski",
    role: "waiter",
    email: "dame@email.com",
  },
  {
    id: 3,
    firstName: "Petko",
    lastName: "Lyutskanov",
    role: "waiter",
    email: "petko@email.com",
  },
];

export default rows;
