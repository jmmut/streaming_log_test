# Test for streaming logs

To run:

```
mvn clean install
java -jar target/demo-0.0.1-SNAPSHOT.jar
```
and then point your browser to `http://localhost:8080/v1/import/asm-reports/GCA_01.1`
(or just `curl http://localhost:8080/v1/import/asm-reports/GCA_01.1`), and then wait for 4 seconds and marvel at a log being updated live. 