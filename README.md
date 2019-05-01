### Install ###
``
$ wsimport -b jaxws-custom.xml -s src/main/java -p com.amazon.ws.model -encoding UTF-8 -keep -Xnocompile http://webservices.amazon.com/AWSECommerceService/AWSECommerceService.wsdl
$ mvn package
``
### Test ###
``
$ java -cp target/soap-client-1.0.jar app.shell.Test
``
