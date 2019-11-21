# Mongo Db replica set setup in windows 10:



### 1) Download Windows .zip from :

https://fastdl.mongodb.org/win32/mongodb-win32-x86_64-2012plus-4.2.1.zip
 
### 2)  set MONGO_HOME and PATH=%MONGO_HOME%\bin
 
 
#### 3) Start mongodb after creating data directories:
D:\Govindaraju\programs\mongoData\data1 , D:\Govindaraju\programs\mongoData\data2 ,D:\Govindaraju\programs\mongoData\data2

### ---------------foreground process - Open powershell as Administroator <br/>

	mongod --port 28017 --dbpath "D:\Govindaraju\programs\mongoData\data1" --replSet rs0

	mongod --port 28018 --dbpath "D:\Govindaraju\programs\mongoData\data2" --replSet rs0

	mongod --port 28019 --dbpath "D:\Govindaraju\programs\mongoData\data3" --replSet rs0


#### 4) Login to mongo to one of the nodes and setup replica set : 

	C:\Users\govindarajuv> mongo --port 28017

		rsConfig = {
		      "_id" : "rs0",
		      "members" : [
			  {
			      "_id" : 0,
			      "host" : "localhost:28017"
			  },
			  {
			      "_id" : 1,
			      "host" : "localhost:28018"
			  },
			  {
			      "_id" : 2,
			      "host" : "localhost:28019"
			  }
		      ]
		  }



		rs.initiate(rsConfig);
		//rs.reinitiate(rsConfig);

### 5) View status 
	rs0:SECONDARY> rs.status();
	
#### 6) Stop ( Ctrl + C)  and start all the nodes
      // net stop MongoDB



# -------------------------  start: background process -------------------------


#### Install Service1: 
	mongod  --port 28017  --dbpath "D:\Govindaraju\programs\mongoData\data1"  --serviceName "mongoservice1"  --serviceDisplayName "mongoservice1"  --replSet rs0 --logpath "D:\Govindaraju\programs\mongoData\log1\log.txt"  --install

#### Install Service 2 : 

	mongod  --port 28018 --dbpath "D:\Govindaraju\programs\mongoData\data3" --serviceName "mongoservice2"  --serviceDisplayName "mongoservice2"  --replSet rs0 --logpath "D:\Govindaraju\programs\mongoData\log2\log.txt"  --install

#### Install Service 3:

	mongod  --port 28018 --dbpath "D:\Govindaraju\programs\mongoData\data3" --serviceName "mongoservice3"  --serviceDisplayName "mongoservice3"  --replSet rs0 --logpath "D:\Govindaraju\programs\mongoData\log3\log.txt"  --install


#### Start the installed services
	PS C:\Windows\system32> net start mongoservice1
	The mongoservice1 service is starting..
	The mongoservice1 service was started successfully.

	PS C:\Windows\system32> net start mongoservice2
	The mongoservice2 service is starting..
	The mongoservice2 service was started successfully.

	PS C:\Windows\system32> net start mongoservice3
	The mongoservice3 service is starting.
	The mongoservice3 service could not be started.

	A service specific error occurred: 100.

	More help is available by typing NET HELPMSG 3547.

	PS C:\Windows\system32>


#### To unistall use option --remove

	mongod  --port 28017  --dbpath "D:\Govindaraju\programs\mongoData\data1"  --serviceName "mongoservice1"  --serviceDisplayName "mongoservice1"  --replSet rs0 --logpath "D:\Govindaraju\programs\mongoData\log1\log.txt"  --remove

	mongod  --port 28018 --dbpath "D:\Govindaraju\programs\mongoData\data3" --serviceName "mongoservice2"  --serviceDisplayName "mongoservice2"  --replSet rs0 --logpath "D:\Govindaraju\programs\mongoData\log2\log.txt"  --remove

	mongod  --port 28018 --dbpath "D:\Govindaraju\programs\mongoData\data3" --serviceName "mongoservice3"  --serviceDisplayName "mongoservice3"  --replSet rs0 --logpath "D:\Govindaraju\programs\mongoData\log3\log.txt"  --remove



## spring boot mongo config
spring:
  data:
    mongodb:
      #uri: "mongodb://localhost:28017,localhost:28018,localhost:28019/dev-tenant?replicaSet=rs0"
      #uri: "mongodb://root:root@localhost:28017,localhost:28018,localhost:28019/dev-tenant?replicaSet=rs0"



 
https://www.mkyong.com/mongodb/how-to-run-mongodb-as-windows-service/
https://stackoverflow.com/questions/2404742/how-to-install-mongodb-on-windows




To run as docker ? 
http://www.tugberkugurlu.com/archive/setting-up-a-mongodb-replica-set-with-docker-and-connecting-to-it-with-a--net-core-app


## Single instance without replicaset
	> mongod  --port 27017  --dbpath "D:\mongoData2701
	7\data"  --serviceName "mongoservice27017"  --serviceDisplayName "mongoservice27017"  --logpath "D:\mongoData27017\log\l
	og.txt"  --install

	> net start mongoservice27017
	> net stop mongoservice27017
	
	

