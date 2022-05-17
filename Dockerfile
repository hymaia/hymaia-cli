FROM hseeberger/scala-sbt:graalvm-ce-21.3.0-java17_1.6.2_3.1.1 as builder

WORKDIR /app

RUN gu install native-image

COPY project project
COPY build.sbt build.sbt
COPY src src

RUN sbt graalvm-native-image:packageBin

FROM scratch

COPY --from=builder /app/target/graalvm-native-image/hymaia /hymaia
