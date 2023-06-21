import { fetchWrapper } from "./fetchWrapper";

export interface Tenant {
    email: string,
    restaurant: {
      id?: string
    },
    name: string,
    surname: string
}

export interface Admin {
  id?: number;
  email: string;
  name: string;
  surname: string;
  password: string;
  blacklisted?: boolean;
  active?: boolean;
  createdAt?: string;
}

export const createTenant = async (tenantData: Tenant): Promise<Tenant> => {
  return fetchWrapper.post<Tenant>("http://localhost:8080/tenant/create", tenantData);
};

export const createAdmin = async (adminData: Admin): Promise<Admin> => {
  return fetchWrapper.post<Admin>("http://localhost:8080/auth/register", adminData);
};


