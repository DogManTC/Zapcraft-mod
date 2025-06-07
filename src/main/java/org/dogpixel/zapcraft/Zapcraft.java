package org.dogpixel.zapcraft;

import net.fabricmc.api.ModInitializer;

public class Zapcraft implements ModInitializer {

    @Override
    public void onInitialize() {
        // Print a message to confirm that Skibi mod is being initialized
        System.out.println("Zapcraft mod is initializing...");

        // Load configuration
        ConfigHandler.loadConfig();

        // Set Pavlok credentials from the config
        DamageEventHandler.setCredentials(
                ConfigHandler.getString("pavlok_email", ""),
                ConfigHandler.getString("pavlok_password", "")
        );

        // Authenticate with Pavlok API
        DamageEventHandler.authenticate();

        // Update connection status and optionally notify the player
        DeviceManager.updateStatus();
        if (ConfigHandler.getBoolean("beep_on_login", false)) {
            DamageEventHandler.sendVibeStimulus("Login test");
        }

        // Initialization complete
        System.out.println("Zapcraft mod initialized successfully, mixins are ready.");
    }
}
