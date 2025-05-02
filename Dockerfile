# Usamos una imagen base con Java 17
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el jar compilado al contenedor
COPY target/*.jar app.jar

# Puerto expuesto (ajusta si usas otro)
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
