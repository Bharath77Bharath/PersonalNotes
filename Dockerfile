FROM eclipse-temurin:21-jdk
COPY target/Personal-Notes.jar Personal-Notes.jar
ENTRYPOINT ["java","-jar","Personal-Notes.jar"]