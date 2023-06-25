import { fetchWrapper } from "./fetchWrapper";
import {
  baseUrl,
  tenantsEndpoint,
  create,
  auth,
  registerEndpoint,
} from "./config.json";

export interface Tenant {
  email: string;
  restaurant: {
    id?: string;
  };
  name: string;
  surname: string;
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
  return fetchWrapper.post<Tenant>(
    `${baseUrl}${tenantsEndpoint}${create}`,
    tenantData
  );
};

export const createAdmin = async (adminData: Admin): Promise<Admin> => {
  return fetchWrapper.post<Admin>(
    `${baseUrl}${auth}${registerEndpoint}`,
    adminData
  );
};
