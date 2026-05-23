import { client } from "./redis_services.ts";

export const generatePdf = async (bookingRecord: any) => {
  const queueName = "pdf:";
  return await client.lPush(
    queueName,
    JSON.stringify({ ...bookingRecord, status: "PENDING" }),
  );
};
