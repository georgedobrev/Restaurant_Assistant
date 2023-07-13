import axios, { AxiosResponse } from "axios";

const get = async <T,>(
  url: string,
  headers?: { "Content-Type": string; Authorization?: string }
): Promise<T> => {
  const response: AxiosResponse<T> = await axios.get(url, { headers });
  return response.data;
};

const post = async <T,>(
  url: string,
  data: T,
  headers?: { "Content-Type": string; Authorization?: string }
): Promise<T> => {
  const response: AxiosResponse<T> = await axios.post(url, data, { headers });
  return response.data;
};

const put = async <T,>(
  url: string,
  data: T,
  headers?: { "Content-Type": string; Authorization?: string }
): Promise<T> => {
  const response: AxiosResponse<T> = await axios.put(url, data, { headers });
  return response.data;
};

const del = async <T,>(
  url: string,
  headers?: { "Content-Type": string; Authorization?: string }
): Promise<T> => {
  const response: AxiosResponse<T> = await axios.delete(url, { headers });
  return response.data;
};

export const fetchWrapper = {
  get,
  post,
  put,
  del,
};
