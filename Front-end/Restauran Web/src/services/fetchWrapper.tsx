import axios, { AxiosResponse } from "axios";

const get = async <T,>(url: string): Promise<T> => {
  const response: AxiosResponse<T> = await axios.get(url);
  return response.data;
};

const post = async <T, R = any>(url: string, data: T): Promise<R> => {
  const response: AxiosResponse<R> = await axios.post(url, data);
  return response.data;
};

const put = async <T,>(url: string, data: T): Promise<T> => {
  const response: AxiosResponse<T> = await axios.put(url, data);
  return response.data;
};

const del = async <T,>(url: string): Promise<T> => {
  const response: AxiosResponse<T> = await axios.delete(url);
  return response.data;
};

export const fetchWrapper = {
  get,
  post,
  put,
  del,
};
