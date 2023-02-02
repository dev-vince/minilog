package dev.vince.log.hook.api;

import dev.vince.log.event.LoggerEventEnum;
import dev.vince.log.hook.HookManager;
import dev.vince.log.logger.Logger;

public abstract class AbstractHook {
    private final String name;
    
    public AbstractHook(final String name) {
        this.name = name;
        HookManager.getInstance().addHook(this);
    }

    public String getName() {
        return name;
    }
    
    public abstract void hook(final Logger logger, final LoggerEventEnum type);
}
