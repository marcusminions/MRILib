package eventlistener;
import java.util.*;
import java.util.function.Consumer;
import eventlistener.BotEvents.*;

public class BotEventManager {
    public enum EventType {
        ARTIFACT_DETECTED,
        ARTIFACT_INTAKE,
        ARTIFACT_OUTTAKE
    }
    private static HashMap<EventType, List<Consumer<BotEvent>>> listeners = new HashMap<>();

    public static void subscribe(EventType eventType, Consumer<BotEvent> listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    public static void broadcast(EventType eventType, BotEvent event) {
        List<Consumer<BotEvent>> eventListeners = listeners.get(eventType);
        if (eventListeners != null) {
            for (Consumer<BotEvent> listener : eventListeners) {
                listener.accept(event);
            }
        }
    }
}