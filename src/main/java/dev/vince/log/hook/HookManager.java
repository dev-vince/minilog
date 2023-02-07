package dev.vince.log.hook;

import java.util.HashMap;
import java.util.Map;

import best.azura.eventbus.core.EventBus;
import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;
import dev.vince.log.MiniLog;
import dev.vince.log.event.LoggerEvent;
import dev.vince.log.hook.api.AbstractHook;
import dev.vince.log.util.ClassUtil;

public final class HookManager {
    private final Map<String, AbstractHook> hooks = new HashMap<>();

    public HookManager(final EventBus eventBus) {
        eventBus.register(this);
    }

    public void loadHooks() {
        this.hooks.clear();

        MiniLog.getInstance().getInternalLogger().info("Loading hooks...");
        
        for (Class<?> clazz : ClassUtil.getClassesRunning()) {
            if (clazz.isAnnotationPresent(Hook.class)) {
                try {
                    addHook((AbstractHook) clazz.newInstance());
                } catch (final InstantiationException | IllegalAccessException e) {
                    MiniLog.getInstance().getInternalLogger().error("Error while loading hook " + clazz.getName() + " : " + e.getMessage());
                }
            }
        }
    }

    public void addHook(final AbstractHook hook) {
        hooks.put(hook.getName(), hook);
        MiniLog.getInstance().getInternalLogger().info("Hook " + hook.getName() + " loaded.");
    }

    public AbstractHook getHook(final String name) {
        return hooks.get(name);
    }

    public void removeHook(final String name) {
        hooks.remove(name);
    }

    public void removeHook(final AbstractHook hook) {
        hooks.remove(hook.getName());
    }

    public void clearHooks() {
        hooks.clear();
    }

    @EventHandler()
    private final Listener<LoggerEvent> loggerEventListener = e -> {
        for(final AbstractHook hook : hooks.values()) {
            hook.hook(e.getLogger(), e.getType());
        }
    };
}
