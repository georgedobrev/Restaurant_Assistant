import { fetchWrapper } from "./fetchWrapper";
import { baseUrl, usersEndpoint } from "./config.json";

export interface User {
  id?: number;
  email: string;
  name: string;
  surname: string;
  roleType: string;
  restaurant?: {
    id: number,
  }
}
export interface Roles {
  email: string,
  name: string,
  surname: string,
  roleType: string,
  restaurant: {
    id: number
}
}

export const editUser = async (userData: User): Promise<User> => {
  return fetchWrapper.put<User>(`${baseUrl}${usersEndpoint}`, userData);
};

export const addUserRole = async (rolesData: Roles): Promise<Roles> => {
  return fetchWrapper.post<Roles>("http://localhost:8080/user/addRole", rolesData) 
}

export const createWaiter = async (rolesData: Roles): Promise<Roles> => {
  return fetchWrapper.post<Roles>("http://localhost:8080/user/waiter", rolesData);
};

export const createAdmin = async (rolesData: Roles): Promise<Roles> => {
  return fetchWrapper.post<Roles>("http://localhost:8080/user/admin", rolesData);
};

export const getUsers = async (id: number): Promise<User | undefined> => {
  return fetchWrapper.get<User | undefined>(`${baseUrl}${usersEndpoint}/${id}/1`);
};

export const deleteUser = async (id: number): Promise<void> => {
  return fetchWrapper.del<void>(`${baseUrl}${usersEndpoint}/${id}`);
};

