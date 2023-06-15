import { fetchWrapper } from "./fetchWrapper";

interface Restaurant {
  id: number;
  name: string;
  tables_count: string;
  address: string;
  phone_number_1: string;
  phone_number_2: string;
}

export const getRestaurants = async () => {
  return fetchWrapper.get("http://localhost:8080/restaurant/getAll");
};

export const getRestaurantByID = async (restaurantId: number) => {
  return fetchWrapper.get(`http://localhost:8080/restaurant/${restaurantId}`);
};

export const createRestaurant = async (restaurantData: Restaurant) => {
  return fetchWrapper.post("http://localhost:8080/restaurant", restaurantData);
};

export const editRestaurant = async (restaurantData: Restaurant) => {
  return fetchWrapper.put("http://localhost:8080/restaurant", restaurantData);
};
