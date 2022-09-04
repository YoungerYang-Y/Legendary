import Request from "@/util/axios/Request";
import type Response from "@/util/axios/Response";

interface User {
  id: number;
  name: string;
}

const request = new Request({});

export const loginApi = (data: {
  password: string;
  username: string;
}): Promise<Response<User>> =>
  request.post({
    url: "/login1",
    data: data,
  });
