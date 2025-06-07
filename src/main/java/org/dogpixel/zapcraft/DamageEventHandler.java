package org.dogpixel.zapcraft;

import net.minecraft.entity.player.PlayerEntity;

public class DamageEventHandler {
    private static String email = "";
    private static String password = "";
    private static boolean authenticated = false;

    public static void setCredentials(String e, String p) {
        email = e;
        password = p;
    }

    public static void authenticate() {
        // Stub authentication
        if (!email.isEmpty() && !password.isEmpty()) {
            authenticated = true;
            System.out.println("Zapcraft: Authenticated with Pavlok API");
        } else {
            System.out.println("Zapcraft: No credentials provided");
        }
    }

    public static void sendApiRequest(float amount, boolean isDead) {
        if (!authenticated) return;
        System.out.println("Zapcraft: sending shock amount " + amount + (isDead ? " (dead)" : ""));
    }

    public static void sendVibeStimulus(String reason) {
        if (!authenticated) return;
        System.out.println("Zapcraft: sending vibe stimulus (" + reason + ")");
    }

    public static void sendRemoteShock(PlayerEntity target, float strength) {
        if (!authenticated) return;
        System.out.println("Zapcraft: remotely shocking " + target.getName().getString() + " with strength " + strength);
    }
}
