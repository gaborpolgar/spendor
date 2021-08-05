FROM adoptopenjdk:14-jre-hotspot
RUN mkdir /opt/app
COPY target/spendor-0.0.1-SNAPSHOT.jar /opt/app/spendor.jar
CMD ["java", "-jar", "/opt/app/spendor.jar"]
