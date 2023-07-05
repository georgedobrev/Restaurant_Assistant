import { fetchWrapper } from "./fetchWrapper";
import { baseUrl, section, allSection } from "./config.json";

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
  return fetchWrapper.post<SectionData>(`${baseUrl}${section}`, sectionData);
};

export const getAllSections = async (
  restaurantId: number
): Promise<SectionData[]> => {
  return fetchWrapper.get<SectionData[]>(
    `${baseUrl}${section}${allSection}${restaurantId}`
  );
};
