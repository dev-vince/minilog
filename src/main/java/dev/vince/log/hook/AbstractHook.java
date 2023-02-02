package dev.vince.log.hook;

import dev.vince.log.event.LoggerEventEnum;
import dev.vince.log.logger.Logger;

public abstract class AbstractHook {
    private final String name;
    private Logger logger;

    public AbstractHook(final String name) {
        this.name = name;
        HookManager.addHook(this);
    }

    public AbstractHook(final String name, final Logger logger) {
        this.logger = logger;
        this.name = name;
        HookManager.addHook(this);
    }

    public String getName() {
        return name;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(final Logger logger) {
        this.logger = logger;
    }
    
    public abstract void hook(final Logger logger, final LoggerEventEnum type);
}
