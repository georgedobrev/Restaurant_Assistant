import { fetchWrapper } from "./fetchWrapper";
import { baseUrl, section, allSection } from "./config.json";
import { storedJWT } from "../screens/constants";

export interface AppTable {
  id: number;
  tableNumber: number;
  qr: {
    id: number;
  };
  restaurant: {
    id: number;
  };
}

export interface SectionData {
  sectionName: string;
  appTables: AppTable[];
}

export const createSection = async (
  sectionData: SectionData
): Promise<SectionData> => {
  const headers = {
    "Content-Type": "application/json",
    Authorization: `Bearer ${storedJWT}`,
  };
  return fetchWrapper.post<SectionData>(
    `${baseUrl}${section}`,
    sectionData,
    headers
  );
};

export const getAllSections = async (
  restaurantId: number
): Promise<SectionData[]> => {
  const headers = {
    "Content-Type": "application/json",
    Authorization: `Bearer ${storedJWT}`,
  };
  return fetchWrapper.get<SectionData[]>(
    `${baseUrl}${section}/${restaurantId}`,
    headers
  );
};
