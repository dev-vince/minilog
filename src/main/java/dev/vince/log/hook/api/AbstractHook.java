package dev.vince.log.hook.api;

import dev.vince.log.MiniLog;
import dev.vince.log.event.LoggerEventEnum;
import dev.vince.log.logger.api.Logger;

public abstract class AbstractHook {
    private final String name;
    
    protected AbstractHook(final String name) {
        this.name = name;
        MiniLog.getInstance().getHookManager().addHook(this);
    }

    public String getName() {
        return name;
    }
    
    public abstract void hook(final Logger logger, final LoggerEventEnum type);
}
