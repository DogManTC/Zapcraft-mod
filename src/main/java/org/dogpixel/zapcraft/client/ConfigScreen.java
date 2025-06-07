package org.dogpixel.zapcraft.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.dogpixel.zapcraft.ConfigHandler;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Very small configuration screen allowing players to toggle a few common
 * options without leaving the game. This is intentionally simple and does not
 * use any external GUI libraries.
 */
public class ConfigScreen extends Screen {
    private boolean vibeBelow;

    protected ConfigScreen() {
        super(Text.literal("Zapcraft Config"));
        vibeBelow = ConfigHandler.getBoolean("vibe_below_threshold", false);
    }

    @Override
    protected void init() {
        int centerX = this.width / 2 - 100;
        int y = this.height / 2 - 10;

        this.addDrawableChild(ButtonWidget.builder(getVibeText(), button -> {
            vibeBelow = !vibeBelow;
            button.setMessage(getVibeText());
        }).dimensions(centerX, y, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Done"), button -> {
            save();
            this.close();
        }).dimensions(centerX, y + 24, 200, 20).build());
    }

    private Text getVibeText() {
        return Text.literal("Vibrate below threshold: " + (vibeBelow ? "ON" : "OFF"));
    }

    private void save() {
        // naive save back to config file
        try (FileWriter writer = new FileWriter("config/zapcraft_config.json")) {
            writer.write("{\n");
            writer.write("  \"pavlok_email\": \"" + ConfigHandler.getString("pavlok_email", "") + "\",\n");
            writer.write("  \"pavlok_password\": \"" + ConfigHandler.getString("pavlok_password", "") + "\",\n");
            writer.write("  \"min_zap_strength\": " + ConfigHandler.getInt("min_zap_strength", 20) + ",\n");
            writer.write("  \"max_zap_strength\": " + ConfigHandler.getInt("max_zap_strength", 80) + ",\n");
            writer.write("  \"death_zap_strength\": " + ConfigHandler.getInt("death_zap_strength", 100) + ",\n");
            writer.write("  \"min_damage_threshold\": " + ConfigHandler.getFloat("min_damage_threshold", 0.5f) + ",\n");
            writer.write("  \"vibe_below_threshold\": " + vibeBelow + "\n");
            writer.write("}\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
