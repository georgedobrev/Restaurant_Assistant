import { fetchWrapper } from "./fetchWrapper";
import { baseUrl } from "./config.json";

export interface Table {
    id: number;
    tableNumber: number;
    qr: {
      id: number;
      hashedUrl: string;
      qrImg: string; 
    };
    restaurant: {
        id: number;
        name: string;
      };
    section: {  
      id: number;
      sectionName: string;
      tableNumbers: number[]; 
    };
  }
  
export const createTable = async (
  tableData: Table
): Promise<Table> => {
  return fetchWrapper.post<Table>(
    `${baseUrl}tables/${tableData.restaurant.id}`,
    tableData
  );
};