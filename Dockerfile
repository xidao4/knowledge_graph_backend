FROM java:8
ADD ./target/backend-coin.jar /app/backend-coin.jar
ADD runboot.sh /app/
WORKDIR /app
RUN chmod a+x runboot.sh
CMD /app/runboot.sh