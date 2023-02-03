# MiniLog
MiniLog is a simple logging library for Java 17+. It is designed to be simple to use and easy to integrate into your project. 

## Usage
### Step 1. Import the library into your project

#### Maven
```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/dev-vince/minilog</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>dev.vince</groupId>
        <artifactId>minilog</artifactId>
        <version>1.0-0</version>
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
MiniLog supports the following log levels: `TRACE`,`DEBUG`, `INFO`, `WARN`, `ERROR`, `FATAL`.
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
## Features
Here are a collection of some of the notable features of MiniLog.

### Logging Headers

You can add a header to the start of each log message. This is useful for adding a timestamp or other information to the start of each log message.

```java
final Logger logger = Logger.createLogger()
        .withName("Logging Example")
        .withLogLevel(LogLevel.WARN)
        .withHeader(LoggingHeaderEnum.BRACKETED) //Adds a bracketed header to the start of each log message
        .build();
```

#### Output
```
[Logging Example] LOG: This is a trace message
```

For the time being, MiniLog has a preset list of headers. However, you can create your own custom headers by implementing the `LoggingHeader` interface in the near future

### Logging Levels

You can change the log level of the logger. This is useful for only logging certain messages to the output streams.

```java
final Logger logger = Logger.createLogger()
        .withName("Logging Example")
        .withLogLevel(LogLevel.WARN) //Only logs messages with a log level of WARN or higher
        .build();
```

#### Output
```
[Logging Example] WARN: This is a warning message
[Logging Example] ERROR: This is an error message
[Logging Example] FATAL: This is a fatal message
```

Only messages with a log level of WARN or higher will be logged to the output streams.

### Logging Formats

You can change the format of the log messages. This is useful for changing the format of the log level or adding custom text to the start of the message.

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

#### Output
```
[Logging Example] WARNING: This is a warning message
```

### Hooking into the logger
MiniLog suppors a system of hooks. Hooks are used to add custom functionality to the logger. For example, you can add a hook to log to a file, or to send a message to a server. 

Hooks are called with a `Logger` and`LoggerEventEnum`. The `Logger` is the logger that the hook is attached to, and the `LoggerEventEnum` is the event that the hook is being called for. 
`LoggerEventEnum` has `PRE` and `POST` as the possible values. `PRE` is called before the message is logged, and `POST` is called after the message is logged.


To create a hook, simply extend either `AbstractHook` or `AbstractLoggerHook` if you want to only have the hook called with a specific logger

The `@Hook` annotation is used to register the hook. Hooks are registered when the logger is built.
#### Example Hook
```java
package dev.vince;


import dev.vince.log.event.LoggerEventEnum;
import dev.vince.log.hook.Hook;
import dev.vince.log.hook.api.AbstractHook;
import dev.vince.log.logger.Logger;

@Hook
public class HookExample extends AbstractHook {
    public HookExample() {
        super("Example hook");
    }

    @Override
    public void hook(final Logger logger, final LoggerEventEnum type) {
        if (type == LoggerEventEnum.PRE) {
            System.out.println("This is a pre hook, it will be executed before the logger logs the message");
        }

        if (type == LoggerEventEnum.POST) {
            System.out.println("This is a post hook, it will be executed after the logger logs the message");
        }
    }
}
```

#### Example Logger Hook
```java
package dev.vince;

import dev.vince.log.LoggerManager;
import dev.vince.log.event.LoggerEventEnum;
import dev.vince.log.hook.Hook;
import dev.vince.log.hook.api.AbstractLoggerHook;
import dev.vince.log.logger.Logger;

@Hook
public class LoggerHookExample extends AbstractLoggerHook {
    public LoggerHookExample() {
        super("Example hook for a specific logger");
    }

    @Override
    public void hook(final Logger logger, final LoggerEventEnum type) {
        super.setLogger(LoggerManager.getLogger("Logger Example")); //This is the logger that will be hooked

        if(logger.equals(super.getLogger())){
            if(type == LoggerEventEnum.PRE){
                System.out.println("This is a pre hook, it will be executed before the logger logs the message");
            }

            if(type == LoggerEventEnum.POST){
                System.out.println("This is a post hook, it will be executed after the logger logs the message");
            }
        }
    }
}
```

These hooks are automatically registered when the logger is built. You can also register hooks manually by calling `HookManager.addHook(AbstractHook hook)`


## Contributing
All contributions are welcome. If you have any suggestions or find any bugs, please create an issue or pull request.

## License
MiniLog is licensed under the GPL-3.0 License. See the [LICENSE](https://github.com/dev-vince/minilog/blob/master/LICENSE) file for more information.

