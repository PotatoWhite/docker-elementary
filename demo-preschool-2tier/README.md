# 순서


## 실행 순서
1. Mysql 실핼(Docker)
~~~
docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=root123 -e MYSQL_DATABASE=test -e MYSQL_USER=potato -e MYSQL_PASSWORD=potato123 -d -p 3306:3306 mysql:8.0.16
~~~