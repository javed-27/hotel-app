import { JWTPayload, jwtVerify } from "jose";

const secret = new TextEncoder().encode(Deno.env.get("SEC_KEY"));

export async function verifyJWT(token: string): Promise<JWTPayload | null> {
  try {
    const { payload } = await jwtVerify(token, secret);
    console.log("JWT is valid:", payload);
    return payload;
  } catch (error) {
    console.error("Invalid JWT:", error);
    return null;
  }
}
