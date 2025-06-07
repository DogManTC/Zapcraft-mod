package net.minecraft.client;
public class MinecraftClient {
    private static final MinecraftClient INSTANCE = new MinecraftClient();
    public Object player;
    public Object textRenderer;
    public static MinecraftClient getInstance() { return INSTANCE; }
    public void setScreen(Object screen) {}
}
