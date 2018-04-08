set mypath=%cd%

cd bin\windows\
Start zookeeper-server-start ../../config/zookeeper.properties
Start kafka-server-start ../../config/server.properties

# wait for 6 seconds
ping 127.0.0.1 -n 6 > nul

kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic digibp-retail

cd %mypath%