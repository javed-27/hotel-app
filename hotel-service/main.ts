import { createApp } from "./src/hotel_controller.ts";

const main = () => {
  console.log("Here");

  const app = createApp();
  Deno.serve({ port: 8000 }, app.fetch);
};

main();
