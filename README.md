An example app that uses the Spring and Axon frameworks to implement CQRS.
The app uses a Spring api gateway and an Oauth2-server for authentication.
It has API-endpoints for CRUD operations on Users and BankAccounts. 
You can run the app in Docker with the following commands:

docker network create --scope=swarm --attachable springbankNet 
docker run -d --name axon-server -p 8024:8024 -p 8123:8124 --network springbankNet --restart always axoniq/axonserver:latest
docker run -it -d --name mongo-container -p 27017:27017 --network springbankNet --restart always -v mongodb_data_container:/data/db mongo:latest
docker run -it -d --name mysql-container -p 3306:3306 --network springbankNet -e MYSQL_ROOT_PASSWORD=springbankRootPsw --restart always -v mysql_data_container:/var/lib/mysql mysql:latest
docker run -it -d --name adminer -p 8080:8080 --network springbankNet -e ADMINER_DEFAULT_SERVER=mysql-container --restart always adminer:latest
Make docker images of the bankacc.cmd.api, bankacc.query.api, user.cmd.api, user.oauth.2.0 and user.query.api.
docker run -d -p 8084:8084 --name oauth2-service --network springbankNet -e "SPRING_PROFILES_ACTIVE=docker" --restart always user-oauth2
Go to the docker folder and run
docker-compose up
