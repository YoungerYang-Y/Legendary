let BASE_URL = "";
const TIME_OUT = 10000;

if (process.env.NODE_ENV === "development") {
  BASE_URL = "url地址";
} else if (process.env.NODE_ENV === "production") {
  BASE_URL = "url地址";
} else {
  BASE_URL = "url地址";
}

export default { BASE_URL, TIME_OUT };
