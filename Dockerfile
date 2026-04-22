FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/quarkus-app/ /app/

EXPOSE 8080

ENV JAVA_OPTS=""
ENV JAVA_APP_JAR=/app/quarkus-run.jar

CMD ["sh", "-c", "exec java $JAVA_OPTS -Dquarkus.http.host=0.0.0.0 -jar \"$JAVA_APP_JAR\""]
