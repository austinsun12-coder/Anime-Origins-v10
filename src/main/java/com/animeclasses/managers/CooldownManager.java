package com.animeclasses.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private final Map<String, Long> cooldowns = new HashMap<>();

    private String key(UUID uuid, String ability) {
        return uuid + ":" + ability;
    }

    public void setCooldown(UUID uuid, String ability, long millis) {
        cooldowns.put(key(uuid, ability), System.currentTimeMillis() + millis);
    }

    public long getRemaining(UUID uuid, String ability) {
        long expires = cooldowns.getOrDefault(key(uuid, ability), 0L);
        return Math.max(0L, expires - System.currentTimeMillis());
    }

    public boolean isReady(UUID uuid, String ability) {
        return getRemaining(uuid, ability) == 0;
    }

    public String getFormattedRemaining(UUID uuid, String ability) {
        long ms = getRemaining(uuid, ability);
        if (ms <= 0) return "§aREADY";
        long secs = (ms + 999) / 1000;
        if (secs >= 60) return "§e" + (secs / 60) + "m " + (secs % 60) + "s";
        return "§c" + secs + "s";
    }

    /** Clears all cooldowns for a player (called on class switch/remove). */
    public void clearAll(UUID uuid) {
        String prefix = uuid.toString() + ":";
        cooldowns.keySet().removeIf(k -> k.startsWith(prefix));
    }
}
