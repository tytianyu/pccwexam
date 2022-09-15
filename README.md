# pccwexam
This is pccw exam project which designed by DDD,it developed by spring boot and run by docker.
## Project structure
The project managed by maven,has four modules:
1. user-api: User api interface support openfeign.
2. user-domain: Domain service of user,including entity,core service and repository interface.
3. user-infrastructure: Infrastructure of user,including some config and basic components.
4. user-application: Backend service of user,implement of user api,can run by nested tomcat of spring boot.
## Run project
1. mvn clean install
2. cd user-application
3. mvn docker:build
4. docker-compose up -d

After execute above commands, you can open link http://localhost:8080/doc.htm in brower.It is document of api and can also test api.
