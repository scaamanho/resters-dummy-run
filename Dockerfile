FROM openjdk:8u181-jre-alpine3.8


ENV JAVA_JAR_NAME="resters-dummy-run-*.jar" \
JVM_SPRING_PROFILES_ACTIVE=dev \
JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom" \
JVM_SERVER_PORT=8080 \
DB_URL=jdbc:h2:file:/database/resters-dummy-run
#JAVA_USER_HOME=/

#COPY target/$JAVA_JAR_NAME $JAVA_USER_HOME
COPY target/$JAVA_JAR_NAME /
VOLUME /database
CMD ["java", "-jar", "/resters-dummy-run-1.0.0-SNAPSHOT.jar"]

EXPOSE $JVM_SERVER_PORT