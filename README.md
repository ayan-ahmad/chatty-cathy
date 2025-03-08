# Chatty Cathy

Chatty Cathy is a command line based chat room

## Running
### Pre-requisites
- JDK 21+
- Maven

(If you have intelliJ these will install automatically or you will be prompted to install them.)
### CLI
#### Server
1. Enter the `server/` folder.
2. Run `mvn spring-boot:run`
#### Client
1. Enter the `client/` folder.
2. Run `mvn spring-boot:run`
### IntelliJ
#### Server
1. Open the project in the folder `server/`
2. Navigate to and run the class `ServerApplication`
#### Client
1. Open the project in the folder `client/`
2. Navigate to and run the class `ClientApplication`
### Optional Parameters
| Parameter                                      | Usage                                                    |
|------------------------------------------------|----------------------------------------------------------|
| -Dspring-boot.run.arguments=--server.port=PORT | Replace port with the port you want the server to run on |

