package org.dogpixel.zapcraft.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.dogpixel.zapcraft.ConfigHandler;
import org.dogpixel.zapcraft.DamageEventHandler;
import org.dogpixel.zapcraft.DeviceManager;
import org.lwjgl.glfw.GLFW;

/**
 * Client entry point registering HUD elements and key bindings.
 */
public class ZapcraftClient implements ClientModInitializer {
    private static KeyBinding openConfigKey;

    @Override
    public void onInitializeClient() {
        DeviceManager.updateStatus();
        HudRenderCallback.EVENT.register(new DeviceStatusOverlay());

        openConfigKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.zapcraft.config", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O, "category.zapcraft"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openConfigKey.wasPressed()) {
                client.setScreen(new ConfigScreen());
            }
        });
    }
}
