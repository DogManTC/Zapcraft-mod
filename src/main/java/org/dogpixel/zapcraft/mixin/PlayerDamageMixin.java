package org.dogpixel.zapcraft.mixin;

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

            float amt = amount - 1;
            System.out.println(player.getRecentDamageSource());

            // Calculate if this damage will cause the player's death
            boolean isDead = (player.getHealth() - amount) <= 0;

            System.out.println("Player took damage: " + amt + ", isDead: " + isDead);

            // Pass damage amount and death status to the damage handler
            DamageEventHandler.sendApiRequest(amt, isDead);
        }
    }
}
