import { fetchWrapper } from "./fetchWrapper";
import { baseUrl, auth, loginEndpoint } from "./config.json";

export interface LoginScreenProps {
  setLoggedIn: (loggedIn: boolean) => void;
  setUserType: (userType: string) => void;
}

export interface LoginRequest {
  googleJwt: string;
  loginRequestRoleType: string;
}

export interface LoginResponse {
  userType: string;
  refreshToken: string;
  token: string;
  appUser: {
    id: number;
  };
}

export const sendJWT = async (
  loginData: LoginRequest
): Promise<LoginResponse> => {
  return fetchWrapper.post<LoginRequest, LoginResponse>(
    `${baseUrl}${auth}${loginEndpoint}`,
    loginData
  );
};
