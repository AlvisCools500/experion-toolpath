package io.github.experion.tools.mob_effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.random.Random;

public class PossessedEffect extends StatusEffect {
    public PossessedEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        Random rand = world.getRandom();

        double vx = rand.nextDouble();
        double vz = rand.nextDouble();

        if (rand.nextBoolean()) {
            vx = -vx;
        }

        if (rand.nextBoolean()) {
            vz = -vz;
        }

        entity.addVelocity(vx,0,vz);

        if (rand.nextBoolean()) {
            Hand hand = Hand.MAIN_HAND;

            if (rand.nextBoolean()) {
                hand = Hand.OFF_HAND;
            }
            entity.swingHand(hand);
        }

        if (rand.nextBoolean()) {
            entity.damage(world,entity.getDamageSources().generic(),0.5f);
        }

        if (!entity.hasStatusEffect(StatusEffects.DARKNESS)) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 40));
        }

        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = Math.max(40 / amplifier, 10);

        if (duration > 0) {
            return duration % i == 0;
        }

        return true;
    }
}
