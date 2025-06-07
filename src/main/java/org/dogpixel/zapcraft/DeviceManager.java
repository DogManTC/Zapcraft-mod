package org.dogpixel.zapcraft;

import java.lang.reflect.Field;

/**
 * Utility to query Pavlok connection status using reflection on the
 * existing {@link DamageEventHandler} class. This avoids modifying the
 * original compiled classes while still allowing runtime checks.
 */
public final class DeviceManager {
    private static boolean connected;

    private DeviceManager() {}

    public static void updateStatus() {
        try {
            Field tokenField = DamageEventHandler.class.getDeclaredField("pavlokToken");
            tokenField.setAccessible(true);
            Object token = tokenField.get(null);
            connected = token != null && !token.toString().isEmpty();
        } catch (ReflectiveOperationException e) {
            connected = false;
        }
    }

    public static boolean isConnected() {
        return connected;
    }
}
