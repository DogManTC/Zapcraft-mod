package net.fabricmc.fabric.api.client.event.lifecycle.v1;
import net.minecraft.client.MinecraftClient;
public class ClientTickEvents {
    public interface EndTick { void onEndTick(MinecraftClient client); }
    public static class Event {
        public void register(EndTick cb) {}
    }
    public static final Event END_CLIENT_TICK = new Event();
}
