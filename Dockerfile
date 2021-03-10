FROM java:8
ADD ./target/backend_coin-0.0.1-SNAPSHOT.jar /app/backend_coin-0.0.1-SNAPSHOT.jar
ADD runboot.sh /app/
WORKDIR /app
RUN chmod a+x runboot.sh
CMD /app/runboot.sh