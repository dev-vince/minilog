package dev.vince.log.event;

import best.azura.eventbus.events.CancellableEvent;
import dev.vince.log.logger.api.Logger;

public final class LoggerEvent extends CancellableEvent{
    private LoggerEventEnum type;
    private String message;
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

    public String getMessage() {
        return message;
    }

    public void setType(final LoggerEventEnum type) {
        this.type = type;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
