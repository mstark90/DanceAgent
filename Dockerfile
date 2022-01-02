# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
FROM openjdk:13-alpine

COPY src/main/resources/*.yml /
COPY build/libs/danceagent*.jar /app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app.jar"]
