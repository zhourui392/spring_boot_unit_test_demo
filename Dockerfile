FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
RUN apk add -U tzdata && \
    cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    date

ADD target/teleus-tele-1.0.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-Xms768m -Xmx768m -Xss256k -Xmn256m"
ENV SPRING_PROFILES_ACTIVE test

VOLUME /home

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar  --spring.profiles.active=$SPRING_PROFILES_ACTIVE" ]