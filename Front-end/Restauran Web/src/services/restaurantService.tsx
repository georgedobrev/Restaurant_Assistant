import { fetchWrapper } from "./fetchWrapper";
import { restaurantsEndpoint } from "./config.json";

export interface Restaurant {
  id: number;
  name: string;
  tablesCount: number;
  address: string;
  phoneNumber: string;
}

export const getRestaurants = async () => {
  return fetchWrapper.get<Restaurant[]>(`${restaurantsEndpoint}/getAll`);
};

export const getRestaurantByID = async (id: number) => {
  const response = await fetchWrapper.get<Restaurant[]>(
    `${restaurantsEndpoint}/${id}`
  );
  return response[0];
};

export const editRestaurant = async (restaurantData: Restaurant) => {
  return fetchWrapper.put<Restaurant>(
    `${restaurantsEndpoint}/${restaurantData.id}`,
    restaurantData
  );
};

export const createRestaurant = async (restaurantData: Restaurant) => {
  return fetchWrapper.post<Restaurant>(
    `${restaurantsEndpoint}`,
    restaurantData
  );
};
