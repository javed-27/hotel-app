import { createClient } from "redis";
export const client = createClient({ url: Deno.env.get("CACHE_URL") });
await client.connect();
