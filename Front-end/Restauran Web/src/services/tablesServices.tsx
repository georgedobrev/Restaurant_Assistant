import { fetchWrapper } from "./fetchWrapper";
import { baseUrl, tablesEndpoint, allTables } from "./config.json";
import { storedRestaurantID } from "../screens/constants";

interface Qr {
  id: number;
  hashedUrl: string;
  qrImg: string[];
}

interface Restaurant {
  id: number;
  name: string;
  tablesCount: number;
  address: string;
  phoneNumber1: string;
  phoneNumber2: string;
  phoneNumber3: string;
  active: boolean;
}

interface Section {
  id: number;
  sectionName: string;
  tableNumbers: string;
  restaurant: Restaurant;
}

export interface Table {
  id: number;
  tableNumber: number;
  qr: Qr;
  occupied: boolean;
  restaurant: Restaurant;
  section: Section;
  capacity: number;
  active: boolean;
  virtualTable: boolean;
}

export type TableData = Pick<Table, "tableNumber" | "capacity">;

export const getAllTables = async (restaurantid: number): Promise<Table[]> => {
  return fetchWrapper.get<Table[]>(
    `${baseUrl}${tablesEndpoint}${restaurantid}${allTables}`
  );
};

export const deleteTable = async (tableNumber: number): Promise<Table[]> => {
  return fetchWrapper.del<Table[]>(
    `${baseUrl}${tablesEndpoint}${storedRestaurantID}/${tableNumber}`
  );
};

export const createTables = async (
  tables: TableData[]
): Promise<TableData[]> => {
  return fetchWrapper.post<TableData[]>(
    `${baseUrl}${tablesEndpoint}${storedRestaurantID}`,
    tables
  );
};
