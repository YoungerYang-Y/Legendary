import Request from "@/util/axios/Request";
import type Response from "@/util/axios/Response";

interface User {
  id: number;
  name: string;
}

const request = new Request({
  baseURL: "/userBusiness",
  headers: {
    "Content-Type": "application/json",
  },
});

export const loginApi = (data: {
  password: string;
  username: string;
}): Promise<Response<User>> =>
  request.post({
    url: "/login1",
    data: data,
  });

export const getUserByUserName = (data: {
  size: number;
  current: number;
}): Promise<Response<User>> =>
  request.post({
    url: "/sys_user/page",
    data: data,
  });
