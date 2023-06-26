import { fetchWrapper } from "./fetchWrapper";
import {
  baseURL,
  notificationsEndpoint,
  getAllNotifications,
  deleteNotification,
} from "../../config.json";

export const getNotifications = async () => {
  try {
    const notifications = await fetchWrapper.get(
      `${baseURL}${notificationsEndpoint}${getAllNotifications}`
    );
    return notifications;
  } catch (error) {
    //TODO error messages from back-end
    return "Failed to fetch notifications: ", error;
  }
};

export const deleteNotifications = async (id) => {
  try {
    const response = await fetchWrapper.del(
      `${baseURL}${notificationsEndpoint}${deleteNotification}/${id}`
    );

    if (!response.ok) {
      //TODO error messages from back-end
      throw new Error(`HTTP error! status: ${response.status}`);
    }
  } catch (error) {
    //TODO error messages from back-end
    return "Failed to delete notification: ", error;
  }
};

export default {
  getNotifications,
  deleteNotifications,
};
