# Chatty Cathy

Chatty Cathy is a command line based chat room

## Set Up

Since there are three projects, with three separate POMs, and chatty-cathy-common is a dependency of the other two, you must build common before anything else.

To do this run `$ mvn clean install` under `chatty-cathy-common/`

In the output it should read something like the following if it works:
`[INFO] Installing C:\Users\GA\Develop\chatty-cathy\chatty-cathy-common\pom.xml to C:\Users\GA\.m2\repository\org\example\chatty-cathy-common\1.0-SNAPSHOT\chatty-cathy-common-1.0-SNAPSHOT.pom`

### IntelliJ IDEA

1. Open the `chatty-cathy-common` project (not its parent folder `chatty-cathy`).
2. On the right hand side press the "m"
2. Press the terminal logo labelled "Execute Maven Goal"
3. Type `mvn clean install` and press enter

### Troubleshooting

1. Generate Maven Sources
2. Reload All Maven Projects
3. Restart your IDE

