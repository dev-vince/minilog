package dev.vince.log.hook;

import java.util.HashMap;
import java.util.Map;

import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;
import dev.vince.log.event.LoggerEvent;

public final class HookManager {
    protected static final Map<String, AbstractHook> hooks = new HashMap<>();

    public static void addHook(final AbstractHook hook) {
        hooks.put(hook.getName(), hook);
    }

    public static AbstractHook getHook(final String name) {
        return hooks.get(name);
    }

    public static void removeHook(final String name) {
        hooks.remove(name);
    }

    public static void removeHook(final AbstractHook hook) {
        hooks.remove(hook.getName());
    }

    @EventHandler()
    private final Listener<LoggerEvent> loggerEventListener = e -> {
        //TODO: finish itterations
        //for(final AbstractHook hook : hooks.values())
            //hook.(e.getLogger());
    };
}
