docker run -it -p 9876:9876 --net fscrawler_default  --name mqnamesrv apache/rocketmq:latest sh mqnamesrv
docker run -it --name mq-broker -p 10911:10911 -p 10909:10909 apache/rocketmq sh mqbroker -c /home/rocketmq/rocketmq-5.1.3/conf/broker.conf
docker run -it --net fscrawler_default --name rocketmq-dashboard -e "JAVA_OPTS=-Drocketmq.namesrv.addr=mqnamesrv:9876" -p 8888:8080 -t apacherocketmq/rocketmq-dashboard:latest

- create a delay topic
 run `./mqadmin updateTopic -n rocketmq-srv:9876 -t demo-delay -c DefaultCluster -a +message.type=DELAY` on broker instance.
- create a transaction topic
 run `./mqadmin updateTopic -n rocketmq-srv:9876 -t demo-tx -c DefaultCluster -a +message.type=TRANSACTION` on broker instance.
