# Chatty Cathy

Chatty Cathy is a command line based chat room

## Set Up

Since there are three projects, with three separate POMs, and common is a dependency of the other two, you must build common before anything else.

To do this run `$ mvn clean install` under `common/`
 
In the output it should read something like the following if it works:
`[INFO] Installing C:\Users\GA\Develop\chatty-cathy\common\pom.xml to C:\Users\GA\.m2\repository\com\chattycathy\common\1.0-SNAPSHOT\common-1.0-SNAPSHOT.pom`

### IntelliJ IDEA

1. Open the `chatty-cathy-common` project (not its parent folder `chatty-cathy`).
2. On the right hand side press the "m"
3. Press the terminal logo labelled "Execute Maven Goal"
4. Type `mvn clean install` and press enter

### Troubleshooting
 
1. Generate Maven Sources
2. Reload All Maven Projects
3. Restart your IDE

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

