# kafka
springboot 集成kafka 简单案例  （docker安装zookeeper 和 kafka）


## docker安装kafka zookeeper
* docker run -d --name zookeeper -p 2181:2181 -t zookeeper  拉取最新版本
* docker run  -d --name kafka -p 9092:9092  --env KAFKA_ADVERTISED_HOST_NAME=localhost  -e KAFKA_ZOOKEEPER_CONNECT=192.168.2.12:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.2.12:9092  -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 -e KAFKA_HEAP_OPTS="-Xmx256M -Xms128M"  wurstmeister/kafka

* 版本对应问题
