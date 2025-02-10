package com.github.experion.tools.mob_effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class GrowFrostEffect extends StatusEffect {
    public GrowFrostEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (!entity.hasStatusEffect(StatusEffects.SLOWNESS)) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40, 2 + amplifier));
        }
        entity.damage(world,entity.getDamageSources().magic(),1.5f);
        return super.applyUpdateEffect(world,entity,amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
