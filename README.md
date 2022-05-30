To get Java to DB connection working add the following to your pom.xml files:
```
<!-- https://mvnrepository.com/artifact/org.postgresql/postgresql -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.3.6</version>
        </dependency>
```
Then download the postgresql JDBC driver from here: https://jdbc.postgresql.org/download/postgresql-42.3.6.jar
and put this in the /apache-tomcat-8.5.78/lib/ folder in your tomcat's home folder.

If it doesn't work try restarting intelliJ first :)
