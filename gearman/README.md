### Install ###
``
$ mvn package
``
### Test worker ###
``
$ java -cp target/java-gearman-0.0.1.jar app.worker.TestWorker 5
``
### Test client ###
``
$ java -cp target/java-gearman-0.0.1.jar app.test.TestClient
``