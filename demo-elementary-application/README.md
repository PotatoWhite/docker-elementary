#
~~~
gradle build -x test
docker-compose up --build
docker-compose up -d --scale elementary-application=8
~~~