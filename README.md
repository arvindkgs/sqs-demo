# AWS SQS demo

1. Add AWS env vars `AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY` to shell
2. Build order-service: `mvn -f order_service/pom.xml package`
3. Deploy to repo: `mvn -f order_service/pom.xml org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile=order_service/target/order-service-0.0.1-SNAPSHOT.jar`
4. Start order-service: `mvn -f order_service/pom.xml spring-boot:run &`
5. Start delivery-service: `mvn -f delivery_service/pom.xml spring-boot:run`
6. Push order: `curl -X POST http://localhost:8080/order  -H "Content-Type: application/json"  --data '{"orderId": 1, "orderCreated":1629791100, "items":[{"product":"Iphone","price":100.0,"quantity":1}]}'`
