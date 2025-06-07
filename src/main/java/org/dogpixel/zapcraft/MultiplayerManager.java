package org.dogpixel.zapcraft;

import net.minecraft.entity.player.PlayerEntity;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MultiplayerManager {
    private static final Map<UUID, Integer> shockCounts = new ConcurrentHashMap<>();
    private static final Map<UUID, Long> lastShock = new ConcurrentHashMap<>();

    public static void recordShock(PlayerEntity player) {
        shockCounts.merge(player.getUuid(), 1, Integer::sum);
        lastShock.put(player.getUuid(), System.currentTimeMillis());
    }

    public static int getShockCount(PlayerEntity player) {
        return shockCounts.getOrDefault(player.getUuid(), 0);
    }

    public static boolean canShock(PlayerEntity player) {
        int cooldown = ConfigHandler.getInt("cooldown_ticks", 20);
        long last = lastShock.getOrDefault(player.getUuid(), 0L);
        long diff = System.currentTimeMillis() - last;
        return diff >= cooldown * 50L; // ticks to ms
    }

    public static void sendRemoteShock(PlayerEntity target, float strength) {
        if (!ConfigHandler.getBoolean("allow_remote_control", false)) {
            return;
        }
        DamageEventHandler.sendRemoteShock(target, strength);
        recordShock(target);
    }
}
