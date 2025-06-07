package org.dogpixel.zapcraft.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.dogpixel.zapcraft.DeviceManager;

/**
 * Simple HUD overlay indicating whether the Pavlok device is connected.
 */
public class DeviceStatusOverlay implements HudRenderCallback {
    @Override
    public void onHudRender(MatrixStack matrices, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) {
            return;
        }

        String status = DeviceManager.isConnected() ? "Pavlok: Connected" : "Pavlok: Disconnected";
        int color = DeviceManager.isConnected() ? 0x00FF00 : 0xFF5555;
        int x = 4;
        int y = 4;
        RenderSystem.enableBlend();
        DrawableHelper.drawTextWithShadow(matrices, client.textRenderer, Text.literal(status), x, y, color);
    }
}
