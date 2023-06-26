import { fetchWrapper } from "./fetchWrapper";

export const getNotifications = async () => {
  try {
    const notifications = await fetchWrapper.get(
      `${baseURL}${notificationsEndpoint}${getAllNotifications}`
    );
    return notifications;
  } catch (error) {
    console.error("Failed to fetch notifications: ", error);
    return [];
  }
};

export const deleteNotification = async (id) => {
  try {
    const response = await fetchWrapper.del(
      `${baseURL}${notificationsEndpoint}${deleteNotification}/${id}`
    );

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
  } catch (error) {
    console.error("Failed to delete notification: ", error);
    throw error;
  }
};

export default {
  getNotifications,
  deleteNotification,
};
