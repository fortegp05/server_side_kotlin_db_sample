# server_side_kotlin_db_sample

DBはMySQL

```
create database db_example;

create user 'springuser'@'%' identified by 'xxxxxx';

grant all on db_example.* to 'springuser'@'%';

create database test_db_example;

grant all on test_db_example.* to 'springuser'@'%';

```

src/main/resources/application.properties

```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/db_example
spring.datasource.username=springuser
spring.datasource.password=xxxxxx
```


テスト用
src/test/resources/application.properties

```
spring.datasource.initialization-mode=always
spring.jpa.hibernate.ddl-auto=create
spring.datasource.url=jdbc:mysql://localhost:3306/test_db_example
spring.datasource.username=springuser
spring.datasource.password=xxxxxx
```


これで以下を実行すればテストが走るはず…。

` ./gradlew test`
