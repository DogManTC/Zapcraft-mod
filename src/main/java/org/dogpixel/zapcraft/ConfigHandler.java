package org.dogpixel.zapcraft;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class ConfigHandler {
    private static final Path CONFIG_PATH = Path.of("config", "zapcraft_config.json");
    private static final Gson GSON = new Gson();
    private static JsonObject config = new JsonObject();

    public static void loadConfig() {
        try {
            if (Files.exists(CONFIG_PATH)) {
                try (InputStreamReader reader = new InputStreamReader(Files.newInputStream(CONFIG_PATH))) {
                    config = GSON.fromJson(reader, JsonObject.class);
                }
            } else {
                config = new JsonObject();
                save();
            }
        } catch (IOException e) {
            System.err.println("Zapcraft: Failed to load config: " + e.getMessage());
            config = new JsonObject();
        }
    }

    private static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(CONFIG_PATH))) {
                GSON.toJson(config, writer);
            }
        } catch (IOException e) {
            System.err.println("Zapcraft: Failed to save config: " + e.getMessage());
        }
    }

    public static boolean getBoolean(String key, boolean def) {
        JsonElement el = config.get(key);
        return el != null ? el.getAsBoolean() : def;
    }

    public static float getFloat(String key, float def) {
        JsonElement el = config.get(key);
        return el != null ? el.getAsFloat() : def;
    }

    public static int getInt(String key, int def) {
        JsonElement el = config.get(key);
        return el != null ? el.getAsInt() : def;
    }

    public static String getString(String key, String def) {
        JsonElement el = config.get(key);
        return el != null ? el.getAsString() : def;
    }

    public static void set(String key, JsonElement value) {
        config.add(key, value);
        save();
    }

    public static Map<String, JsonElement> getAll() {
        return config.asMap();
    }
}
