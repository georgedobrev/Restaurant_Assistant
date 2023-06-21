import { fetchWrapper } from "./fetchWrapper";
import { baseUrl, usersEndpoint } from "./config.json";

export interface User {
  id?: number;
  email: string;
  name: string;
  surname: string;
  password?: string;
  blacklisted?: boolean;
  active?: boolean;
  createdAt?: string;
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

export const getUsers = async (id: number): Promise<User | undefined> => {
  return fetchWrapper.get<User | undefined>(`${baseUrl}${usersEndpoint}/${id}`);
};

export const createUser = async (userData: User): Promise<User> => {
  return fetchWrapper.post<User>("http://localhost:8080/auth/register", userData);
};

export const editUser = async (userData: User, id: number): Promise<User> => {
  return fetchWrapper.put<User>(`${baseUrl}${usersEndpoint}/${id}`, userData);
};

export const deleteUser = async (id: number): Promise<void> => {
  return fetchWrapper.del<void>(`${baseUrl}${usersEndpoint}/${id}`);
};

export const addUserRole = async (rolesData: Roles): Promise<Roles> => {
  return fetchWrapper.post<Roles>("http://localhost:8080/user/addRole", rolesData) 
}
