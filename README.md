# kafka
springboot 集成kafka 简单案例  （docker安装zookeeper 和 kafka）


## 一、docker安装kafka zookeeper
* docker run -d --name zookeeper -p 2181:2181 -t zookeeper  拉取最新版本
* docker run  -d --name kafka -p 9092:9092  --env KAFKA_ADVERTISED_HOST_NAME=localhost  -e KAFKA_ZOOKEEPER_CONNECT=192.168.2.12:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.2.12:9092  -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 -e KAFKA_HEAP_OPTS="-Xmx256M -Xms128M"  wurstmeister/kafka

* 版本对应问题

## 二、idea 一键部署docker（有很多方案，docker-componse.yml<一键集群了解一下>--一般都是linux环境操作）

#### https://www.cnblogs.com/hei12138/p/ideausedocker.html   按照此网站步骤操作

* docker 设置：

 ![image](https://github.com/17661977890/kafka/blob/master/src/main/resources/image/760273-20171004163227693-1287318838.png)

* 安装idea docker插件:

 ![image](https://github.com/17661977890/kafka/blob/master/src/main/resources/image/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20190710100024.png)

* 配置测试联通:（一般docker都要开启远程访问连接，貌似windows默认开启，所以如果是其他环境安装docker，要看看）

 ![image](https://github.com/17661977890/kafka/blob/master/src/main/resources/image/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20190710100159.png)
 
* 已经编写好的本地项目，并本地打包  mvn clean package:
 
* 编写Dockerfile(文件名字不能写错)--具体编写内容可以网上查看，注意 生成得jar包 要和Dockerfile在一个文件夹下，如果不在，在Dockerfile ADD编写得时候要写正确相对对路径（我是放在一个文件夹下了）
 
 ![image](https://github.com/17661977890/kafka/blob/master/src/main/resources/image/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20190710100827.png)
 
* 配置docker 发布部署启动项:

 ![image](https://github.com/17661977890/kafka/blob/master/src/main/resources/image/760273-20171004170451161-1746553335.png)
 ![image](https://github.com/17661977890/kafka/blob/master/src/main/resources/image/760273-20171004170919865-309353323.png)

* run 执行 看日志是否发布成功：

 ![image](https://github.com/17661977890/kafka/blob/master/src/main/resources/image/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20190710100917.png)

* 测试（注意端口是docker端暴露得端口，可以在docker 我们发布的容器查看日志）

 ![image](https://github.com/17661977890/kafka/blob/master/src/main/resources/image/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20190710101020.png)
 ![image](https://github.com/17661977890/kafka/blob/master/src/main/resources/image/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20190710095657.png)


#### 项目发布docker过程遇到得报错： 

 * (1)Dockerfile中jar得路径不对，找不到本地打jar包;
 * (2)配置启动项时候，绑定得映射端口 之前有重复得;
 * (3)成功发布好，但是日志显示没有 no main....类似打得jar有问题
 
#### 本地、docker 安装启动kafka报错：

 * (1)oom 等 类似于jvm参数得问题--配置启动文件中得jvm参数（没有解决--很多事linux环境解决方案）
 * (2)docker 拉取镜像报错，重启docker  获取换一下镜像路径
