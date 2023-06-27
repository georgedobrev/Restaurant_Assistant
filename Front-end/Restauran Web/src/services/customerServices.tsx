import { fetchWrapper } from "./fetchWrapper";
import { baseUrl, notificationEndpoint, create } from "./config.json";

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

//TODO make appUserID and appTableID dynamic
export const sendNotification = async (
  appUserId: number,
  appTableId: number,
  requestType: string,
  message: string
) => {
  const url = `${baseUrl}${notificationEndpoint}${create}`;
  const data: NotificationData = {
    appUser: {
      id: 1, //TODO
    },
    appTable: {
      id: 1, //TODO
    },
    requestType: requestType,
    message: message,
  };

  return fetchWrapper.post<NotificationData>(url, data);
};
