FROM openjdk:11-jdk-slim
ADD target/currencyConverter-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD java -jar currencyConverter-0.0.1-SNAPSHOT.jar
