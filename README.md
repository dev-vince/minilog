# MiniLog
MiniLog is a simple logging library for Java 17+. It is designed to be simple to use and easy to integrate into your project. 

## Usage
### Step 1. Import the library into your project

#### Maven
```xml
<distributionManagement>
    <repository>
        <id>github</id>
        <name>GitHub OWNER Apache Maven Packages</name>
        <url>https://maven.pkg.github.com/dev-vince/minilog</url>
    </repository>
</distributionManagement>

<dependencies>
    <dependency>
        <groupId>dev.vince</groupId>
        <artifactId>minilog</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

or by downloading the jar from the [packages](https://github.com/dev-vince/minilog/packages/1783725) page.

### Step 2. Create a new logger
 Here are some samples of code to help you get started creating new loggers.

#### Create a new logger with a custom name
```java
final Logger logger = Logger.createLogger()
        .withName("Logging Example")
        .build();
```

#### Create a new logger with a custom name and custom log level
```java
final Logger logger = Logger.createLogger()
        .withName("Logging Example")
        .withLogLevel(LogLevel.WARN)
        .build();
```

#### Create a new logger with a custom name, custom log level and custom log format
```java
final Logger logger = Logger.createLogger()
        .withName("Logging Example")
        .withLogLevel(LogLevel.WARN)
        .withFormat(new LoggerFormat.Builder()
            .withLogFormat("(%s): %s")// The first %s is the log level and the second is the message, The default format is "%s: %s"
            .withLevelFormat(LoggingLevelEnum.WARN, "WARNING") //Changes the format of the log level to WARNING (by default WARN)
            .build()) 
        .build();
```

#### You can also add multiple custom output streams to the logger
System.out is added by default, however you can add your own output streams (for example a file)
```java
final Logger logger = Logger.createLogger()
        .withName("Logging Example")
        .withLogLevel(LogLevel.WARN)
        .withFormat(new LoggerFormat.Builder()
            .withLogFormat("(%s): %s")// The first %s is the log level and the second is the message, The default format is "%s: %s"
            .withLevelFormat(LoggingLevelEnum.WARN, "WARNING") //Changes the format of the log level to WARNING (by default WARN)
            .build())
        .withOutput(new PrintStream("output.txt")) //Will now add all logs to output.txt
        .build();
```
### Step 3. Log messages
Tinylog supports the following log levels: `TRACE`,`DEBUG`, `INFO`, `WARN`, `ERROR`, `FATAL`.
To log a message, simply call the appropriate method in the logger.

```java
// Logs a messageto the output streams
logger.trace("This is a trace message");
logger.debug("This is a debug message");
logger.info("This is an info message");
logger.warn("This is a warning message");
logger.error("This is an error message");
logger.fatal("This is a fatal message");

// You can also use the log method to log multiple messages per call (this is useful for logging exceptions, or multi line messages)
logger.trace("This is a trace message", "This is another trace message");
logger.debug("This is a debug message", "This is another debug message");
logger.info("This is an info message", "This is another info message");
logger.warn("This is a warning message", "This is another warning message");
logger.error("This is an error message", "This is another error message");
logger.fatal("This is a fatal message", "This is another fatal message");
```

#### Output
```
[Logging Example] LOG: This is a trace message
[Logging Example] DEBUG: This is a debug message
[Logging Example] INFO: This is an info message
[Logging Example] WARN: This is a warning message
[Logging Example] ERROR: This is an error message
[Logging Example] FATAL: This is a fatal message
[Logging Example] LOG: This is a trace message
[Logging Example] LOG: This is another trace message
[Logging Example] DEBUG: This is a debug message
[Logging Example] DEBUG: This is another debug message
[Logging Example] INFO: This is an info message
[Logging Example] INFO: This is another info message
[Logging Example] WARN: This is a warning message
[Logging Example] WARN: This is another warning message
[Logging Example] ERROR: This is an error message
[Logging Example] ERROR: This is another error message
[Logging Example] FATAL: This is a fatal message
[Logging Example] FATAL: This is another fatal message
```
