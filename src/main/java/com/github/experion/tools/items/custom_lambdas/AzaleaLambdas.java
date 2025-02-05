package com.github.experion.tools.items.custom_lambdas;

import com.github.experion.tools.initializer.ModParticles;
import com.github.experion.tools.items.tool_lambdas.ExistsLambdas;
import com.github.experion.tools.items.tool_lambdas.TriggerLambdas;
import com.github.experion.tools.lib.EasyParticle;
import com.github.experion.tools.lib.ToolLib;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AzaleaLambdas implements TriggerLambdas {
    @Override
    public ExistsLambdas exists() {
        return new ExistsLambdas().PostHit();
    }

    @Override
    public void main_trigger(ItemStack stack, World world, Vec3d pos, LivingEntity entity, ToolLib.TriggerType trigType) {
        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;
            Vec3d respos = pos;

            if (trigType != ToolLib.TriggerType.HIT) {
                respos.add(0.5,0.5,0.5);
            }

            EasyParticle.SummonSpreadedParticle(serverWorld,respos,new Vec3d(0,0,0),8, ModParticles.AZALEA_EFFECT);

            if (trigType == ToolLib.TriggerType.MINE_BLOCK || (trigType == ToolLib.TriggerType.HIT && entity.isDead())) {
                DustParticleEffect dust1 = EasyParticle.getColoredDust(0.8157f, 0.4824f, 0.8902f,1f);
                DustParticleEffect dust2 = EasyParticle.getColoredDust(0.4392f, 0.5725f, 0.1765f, 1.0f);

                EasyParticle.SummonSpreadedParticle(
                        serverWorld,
                        respos,
                        new Vec3d(0.0F,0.0F,0.0F),
                        serverWorld.getRandom().nextBetween(5,10),
                        dust1
                );
                EasyParticle.SummonSpreadedParticle(
                        serverWorld,
                        respos,
                        new Vec3d(0.0F,0.0F,0.0F),
                        serverWorld.getRandom().nextBetween(5,10),
                        dust2
                );
            }
        }
    }

    @Override
    public void onPostHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();

        if (!world.isClient) {
            if (target.isDead()) {
                ServerWorld serverWorld = (ServerWorld) world;

                serverWorld.spawnParticles(ModParticles.AZALEA_EXPLOSIVE, target.getX(), target.getY() + 0.5, target.getZ(), 1,0,0,0,0);
            }

            main_trigger(stack,world,target.getPos(),target, ToolLib.TriggerType.HIT);
        }
    }

    @Override
    public String getLambdasName() {
        return "Azalea";
    }
}
