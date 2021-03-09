FROM java:8
ADD ./target/backend_coin.jar /app/backend_coin.jar
ADD runboot.sh /app/
WORKDIR /app
RUN chmod a+x runboot.sh
CMD /app/runboot.sh