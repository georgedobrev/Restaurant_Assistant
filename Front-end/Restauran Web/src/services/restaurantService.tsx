import { fetchWrapper } from "./fetchWrapper";
import {
  baseUrl,
  restaurantsEndpoint,
  restaurants,
  getRestaurantsByAdmin,
} from "./config.json";

export interface Restaurant {
  id?: number;
  name: string;
  tablesCount: number;
  address: string;
  phoneNumber1: string;
}

export interface RestaurantObj {
  restaurantDto: Restaurant;
  userId: number;
}

export const getRestaurants = async (): Promise<Restaurant[]> => {
  return fetchWrapper.get<Restaurant[]>(
    `${baseUrl}${restaurantsEndpoint}${restaurants}`
  );
};

export const getRestaurantByID = async (id: number) => {
  return fetchWrapper.get<Restaurant[]>(
    `${baseUrl}${restaurantsEndpoint}/${id}`
  );
};

export const getRestaurantsByAdminID = async (
  userId: number
): Promise<Restaurant[]> => {
  const response = await fetchWrapper.get<Restaurant[]>(
    `${baseUrl}${restaurantsEndpoint}${getRestaurantsByAdmin}/${userId}`
  );
  return response;
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
  restaurantData: RestaurantObj
): Promise<RestaurantObj> => {
  return fetchWrapper.post<RestaurantObj>(
    `${baseUrl}${restaurantsEndpoint}`,
    restaurantData
  );
};
