import { fetchWrapper } from "./fetchWrapper";
import { usersEndpoint } from "./config.json";

export interface User {
  id?: number;
  email: string;
  name: string;
  surname: string;
  blacklisted?: boolean;
  active?: boolean;
  createdAt?: string;
}

export const getUsers = async (id: number): Promise<User | undefined> => {
  return fetchWrapper.get<User | undefined>(`${usersEndpoint}/${id}`);
};

export const createUser = async (userData: User): Promise<User> => {
  return fetchWrapper.post<User>(usersEndpoint, userData);
};

export const editUser = async (userData: User, id: number): Promise<User> => {
  return fetchWrapper.put<User>(`${usersEndpoint}/${id}`, userData);
};

export const deleteUser = async (id: number): Promise<void> => {
  return fetchWrapper.del<void>(`${usersEndpoint}/${id}`);
};
