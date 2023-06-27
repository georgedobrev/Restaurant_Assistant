import { fetchWrapper } from "./fetchWrapper";
import { baseUrl, usersEndpoint, addRole, waiter, admin } from "./config.json";

export interface User {
  id?: number;
  email: string;
  name: string;
  surname: string;
  roleType: string;
  restaurant: {
    id?: number;
  };
}
export interface Roles {
  email: string;
  name?: string;
  surname?: string;
  roleType: string;
  restaurant: {
    id: number;
  };
}

export const editUser = async (userData: User): Promise<User> => {
  return fetchWrapper.put<User>(`${baseUrl}${usersEndpoint}`, userData);
};

export const addUserRole = async (rolesData: Roles): Promise<Roles> => {
  return fetchWrapper.post<Roles>(
    `${baseUrl}${usersEndpoint}${addRole}`,
    rolesData
  );
};

export const createWaiter = async (rolesData: Roles): Promise<Roles> => {
  return fetchWrapper.post<Roles>(
    `${baseUrl}${usersEndpoint}${waiter}`,
    rolesData
  );
};

export const createAdmin = async (rolesData: Roles): Promise<Roles> => {
  return fetchWrapper.post<Roles>(
    `${baseUrl}${usersEndpoint}${admin}`,
    rolesData
  );
};

//TODO
//MAKE RESTAURANTS DYNAMIC WHEN TENANT MENU IS DONE
export const getUsers = async (id: number): Promise<User | undefined> => {
  return fetchWrapper.get<User | undefined>(
    `${baseUrl}${usersEndpoint}/${id}/1`
  );
};

export const deleteUser = async (id: number): Promise<void> => {
  return fetchWrapper.del<void>(`${baseUrl}${usersEndpoint}/${id}`);
};
