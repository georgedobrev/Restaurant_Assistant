import { fetchWrapper } from "./fetchWrapper";
import { baseUrl, notificationEndpoint } from "./config.json";
import { storedJWT } from "../screens/constants";

interface AppUser {
  id: number;
}

interface AppTable {
  id: number;
}

interface NotificationData {
  appUser: AppUser;
  appTable: AppTable;
  requestType: string;
  message: string;
}

// TODO: make appUserID and appTableID dynamic
export const sendNotification = async (
  appUserId: number,
  appTableId: number,
  requestType: string,
  message: string
) => {
  const url = `${baseUrl}${notificationEndpoint}`;
  const headers = {
    "Content-Type": "application/json",
    Authorization: `Bearer ${storedJWT}`,
  };
  const data: NotificationData = {
    appUser: {
      id: 1, // TODO: Replace with the dynamic appUserId
    },
    appTable: {
      id: 1, // TODO: Replace with the dynamic appTableId
    },
    requestType: requestType,
    message: message,
  };

  return fetchWrapper.post<NotificationData>(url, data, headers);
};
