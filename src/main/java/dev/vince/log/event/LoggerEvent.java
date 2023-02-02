package dev.vince.log.event;

import best.azura.eventbus.events.CancellableEvent;
import dev.vince.log.logger.Logger;

public final class LoggerEvent extends CancellableEvent{
    private LoggerEventEnum type;
    private final Logger logger;

    public LoggerEvent(final LoggerEventEnum type, final Logger logger) {
        this.type = type;
        this.logger = logger;
    }

    public LoggerEventEnum getType() {
        return type;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setType(final LoggerEventEnum type) {
        this.type = type;
    }
}
