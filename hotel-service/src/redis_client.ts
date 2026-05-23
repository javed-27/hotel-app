import { createClient } from "npm:redis@^4.5";

const client = createClient({
  url: "redis://localhost:6379",
});

await client.connect();

export default client;
