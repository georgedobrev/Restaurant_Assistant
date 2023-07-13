import { fetchWrapper } from "./fetchWrapper";
import { baseUrl, usersEndpoint } from "./config.json";
import { storedJWT } from "../screens/constants";

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
  const headers = {
    "Content-Type": "application/json",
    Authorization: `Bearer ${storedJWT}`,
  };
  return fetchWrapper.put<User>(
    `${baseUrl}${usersEndpoint}/details`,
    userData,
    headers
  );
};

export const addEmployee = async (rolesData: Roles): Promise<Roles> => {
  const headers = {
    "Content-Type": "application/json",
    Authorization: `Bearer ${storedJWT}`,
  };
  return fetchWrapper.post<Roles>(
    `${baseUrl}${usersEndpoint}/employee`,
    rolesData,
    headers
  );
};

//TODO
//MAKE RESTAURANTS DYNAMIC WHEN TENANT MENU IS DONE
export const getUserByEmail = async (
  email: string
): Promise<User | undefined> => {
  const headers = {
    "Content-Type": "application/json",
    Authorization: `Bearer ${storedJWT}`,
  };

  return fetchWrapper.get<User | undefined>(
    `${baseUrl}${usersEndpoint}`,
    headers
  );
};

export const deleteUserByEmail = async (email: string): Promise<void> => {
  return fetchWrapper.del<void>(
    `${baseUrl}${usersEndpoint}/${encodeURIComponent(email)}`
  );
};
