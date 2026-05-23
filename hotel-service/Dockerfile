FROM denoland/deno:2.4.5

WORKDIR /app

COPY hotel_controller.ts .
COPY hotel_service.ts .
COPY hotel_repo.ts .
COPY deno.json .
COPY deno.lock .

EXPOSE 8000

CMD ["run", "-A", "hotel_controller.ts"]
