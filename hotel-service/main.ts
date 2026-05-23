import app from "./hotel_controller.ts";
console.log("HEr ");

Deno.serve({ port: 8000 }, app.fetch);
