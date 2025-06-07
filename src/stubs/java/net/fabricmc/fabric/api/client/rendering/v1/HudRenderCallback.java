package net.fabricmc.fabric.api.client.rendering.v1;
import net.minecraft.client.util.math.MatrixStack;
public interface HudRenderCallback {
    void onHudRender(MatrixStack matrices, float tickDelta);
    class Event {
        public void register(HudRenderCallback callback) {}
    }
    Event EVENT = new Event();
}
