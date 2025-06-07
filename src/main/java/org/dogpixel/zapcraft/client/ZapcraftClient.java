package org.dogpixel.zapcraft.client;

import net.fabricmc.api.ClientModInitializer;
import org.dogpixel.zapcraft.ConfigHandler;
import org.dogpixel.zapcraft.DamageEventHandler;

public class ZapcraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("Zapcraft client initializing");
        ConfigHandler.loadConfig();

        DamageEventHandler.setCredentials(
                ConfigHandler.getString("pavlok_email", ""),
                ConfigHandler.getString("pavlok_password", "")
        );

        DamageEventHandler.authenticate();
        System.out.println("Zapcraft client initialized");
    }
}
