import { fetchWrapper } from "./fetchWrapper";
import { baseUrl, restaurantsEndpoint, admin } from "./config.json";
import { storedJWT } from "../screens/constants";

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
  const headers = {
    "Content-Type": "application/json",
    Authorization: `Bearer ${storedJWT}`,
  };
  return fetchWrapper.get<Restaurant[]>(
    `${baseUrl}${restaurantsEndpoint}`,
    headers
  );
};

export const getRestaurantByID = async (
  id: number
): Promise<Restaurant | undefined> => {
  const headers = {
    "Content-Type": "application/json",
    Authorization: `Bearer ${storedJWT}`,
  };
  const response = await fetchWrapper.get<Restaurant>(
    `${baseUrl}${restaurantsEndpoint}/${id}`,
    headers
  );
  return response;
};

export const getRestaurantsByAdminID = async (
  userId: number
): Promise<Restaurant[]> => {
  const headers = {
    "Content-Type": "application/json",
    Authorization: `Bearer ${storedJWT}`,
  };
  const response = await fetchWrapper.get<Restaurant[]>(
    `${baseUrl}${restaurantsEndpoint}${admin}/${userId}`,
    headers
  );
  return response;
};

export const editRestaurant = async (
  restaurantData: Restaurant
): Promise<Restaurant> => {
  const headers = {
    "Content-Type": "application/json",
    Authorization: `Bearer ${storedJWT}`,
  };
  return fetchWrapper.put<Restaurant>(
    `${baseUrl}${restaurantsEndpoint}/${restaurantData.id}`,
    restaurantData,
    headers
  );
};

export const createRestaurant = async (
  restaurantData: RestaurantObj
): Promise<RestaurantObj> => {
  const headers = {
    "Content-Type": "application/json",
    Authorization: `Bearer ${storedJWT}`,
  };
  return fetchWrapper.post<RestaurantObj>(
    `${baseUrl}${restaurantsEndpoint}`,
    restaurantData,
    headers
  );
};
