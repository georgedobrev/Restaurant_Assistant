import { fetchWrapper } from "./fetchWrapper";

interface User {
  id: number;
  email: string;
  name: string;
  surname: string;
  blacklisted: boolean;
  active: boolean;
  createdAt: string;
}

export const getUsers = async (id: number) => {
  return fetchWrapper.get(`http://localhost:8080/user/${id}`);
};

export const createUser = async (userData: User) => {
  return fetchWrapper.post("http://localhost:8080/user", userData);
};

export const deleteUser = async (id: number) => {
  return fetchWrapper.del(`http://localhost:8080/user/${id}`);
};
