package org.dogpixel.zapcraft.mixin;

import org.dogpixel.zapcraft.ConfigHandler;
import org.dogpixel.zapcraft.DamageEventHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class PlayerDamageMixin {

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        if ((Object) this instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) (Object) this;

            // Get the minimum damage threshold and vibe enable flag from the config
            float minDamageThreshold = ConfigHandler.getFloat("min_damage_threshold", 0.5f);
            boolean vibeBelowThreshold = ConfigHandler.getBoolean("vibe_below_threshold", false);

            if (amount > minDamageThreshold) {
                // Damage is above threshold, handle normally
                float adjustedAmount = amount;
                System.out.println(player.getRecentDamageSource());

                // Calculate if this damage will cause the player's death
                boolean isDead = (player.getHealth() - amount) <= 0;

                System.out.println("Player took damage: " + adjustedAmount + ", isDead: " + isDead);

                // Pass damage amount and death status to the damage handler
                DamageEventHandler.sendApiRequest(adjustedAmount, isDead);
            } else if ((amount <= minDamageThreshold) & (vibeBelowThreshold)) {
                // Damage is below threshold, send "vibe" stimulus
                System.out.println("Damage below threshold. Sending vibe stimulus.");
                DamageEventHandler.sendVibeStimulus("Damage below threshold: " + amount);
            } else {
                System.out.println("Damage below threshold. No action taken.");
            }
        }
    }
}
