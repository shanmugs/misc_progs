FROM openjdk:8
ENV APP_FILE SpringBootMongo-0.1.0.jar
ENV APP_HOME /usr/app
EXPOSE 8000
COPY build/libs/$APP_FILE $APP_HOME/
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar    $APP_FILE"]