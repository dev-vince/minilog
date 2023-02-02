package dev.vince.log.hook;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import best.azura.eventbus.core.EventBus;
import best.azura.eventbus.handler.EventHandler;
import best.azura.eventbus.handler.Listener;
import dev.vince.log.event.LoggerEvent;
import dev.vince.log.hook.api.AbstractHook;

public final class HookManager {
    private static final HookManager instance = new HookManager();
    private static final EventBus eventBus = new EventBus();

    protected final Map<String, AbstractHook> hooks = new HashMap<>();

    private HookManager() {
        final Reflections reflections = new Reflections();
        final Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Hook.class);

        System.out.println("Found " + annotated.size() + " hooks");
        eventBus.register(this);

        for(final Class<?> clazz : annotated) {
            try {
                System.out.println("Hook found: " + clazz.getName());
                addHook((AbstractHook) clazz.newInstance());
            } catch (final InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void addHook(final AbstractHook hook) {
        hooks.put(hook.getName(), hook);
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

    public void callEvent(final LoggerEvent event) {
        eventBus.call(event);
    }

    @EventHandler()
    private final Listener<LoggerEvent> loggerEventListener = e -> {
        for(final AbstractHook hook : hooks.values()) {
            hook.hook(e.getLogger(), e.getType());
        }
    };

    public static HookManager getInstance() {
        return instance;
    }
}
