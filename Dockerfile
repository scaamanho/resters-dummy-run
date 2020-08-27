FROM openjdk:8u181-jre-alpine3.8


ENV JAVA_JAR_NAME="resters-dummy-run-*.jar" \
JVM_SPRING_PROFILES_ACTIVE=dev \
JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom"
JVM_SERVER_PORT=8080
JAVA_USER_HOME=/

#COPY target/$JAVA_JAR_NAME $JAVA_USER_HOME
COPY target/$JAVA_JAR_NAME /

CMD ["java", "-jar", "$JAVA_USER_HOME$JAVA_JAR_NAME"]

EXPOSE $JVM_SERVER_PORT