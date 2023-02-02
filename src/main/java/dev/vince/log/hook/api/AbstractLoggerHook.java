package dev.vince.log.hook.api;

import dev.vince.log.logger.Logger;

public abstract class AbstractLoggerHook extends AbstractHook{
    private Logger logger;

    public AbstractLoggerHook(final String name) {
        super(name);
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(final Logger logger) {
        this.logger = logger;
    }
}
