FROM eclipse-temurin

RUN apt-get update

WORKDIR /app

COPY . /app

ENTRYPOINT [ "./mvnw", "spring-boot:run"]

