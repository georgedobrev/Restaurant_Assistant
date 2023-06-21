import { fetchWrapper } from "./fetchWrapper";
import { baseUrl, restaurantsEndpoint, restaurants } from "./config.json";

export interface Restaurant {
  id?: number;
  name: string;
  tablesCount: number;
  address: string;
  phoneNumber: string;
}

export const getRestaurants = async (): Promise<Restaurant[]> => {
  return fetchWrapper.get<Restaurant[]>(
    `${baseUrl}${restaurantsEndpoint}${restaurants}`
  );
};

export const getRestaurantByID = async (id: number): Promise<Restaurant> => {
  const response = await fetchWrapper.get<Restaurant[]>(
    `${baseUrl}${restaurantsEndpoint}/${id}`
  );
  return response[0];
};

export const editRestaurant = async (
  restaurantData: Restaurant
): Promise<Restaurant> => {
  return fetchWrapper.put<Restaurant>(
    `${baseUrl}${restaurantsEndpoint}/${restaurantData.id}`,
    restaurantData
  );
};

export const createRestaurant = async (
  restaurantData: Restaurant
): Promise<Restaurant> => {
  return fetchWrapper.post<Restaurant>(
    `${baseUrl}${restaurantsEndpoint}`,
    restaurantData
  );
};
