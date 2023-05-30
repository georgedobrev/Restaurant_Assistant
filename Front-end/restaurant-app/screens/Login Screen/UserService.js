import axios from "axios";
import config from "../../config.json";

export const fetchUserInfo = async (accessToken) => {
  const response = await axios.get(config.googleApi, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  if (response.status !== 200) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }

  return response.data;
};
