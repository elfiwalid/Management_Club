# Étape 1 : image de base Java
FROM eclipse-temurin:17-jdk-alpine

# Étape 2 : répertoire de travail
WORKDIR /gestion_club

# Étape 3 : copier le fichier jar
COPY target/gestion_club-0.0.1-SNAPSHOT.jar app.jar

# Étape 4 : commande de démarrage
ENTRYPOINT ["java", "-jar", "app.jar"]
