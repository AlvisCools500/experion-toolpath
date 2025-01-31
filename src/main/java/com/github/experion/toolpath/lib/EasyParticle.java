package com.github.experion.toolpath.lib;

import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class EasyParticle {
    public static DustParticleEffect getColoredDust(PrimalDoubleColor primalCol, float scale) {
        return getColoredDust(primalCol.getRED(), primalCol.getGREEN(), primalCol.getBLUE(), scale);
    }

    public static DustParticleEffect getColoredDust(float red, float green, float blue, float scale) {
        return new DustParticleEffect(new Vector3f(red,green,blue), scale);
    }

    public static void SummonSpreadedParticle(ServerWorld world, Vec3d pos, Vec3d velocity3, int Amount, ParticleEffect effect) {
        for (int i = 1; i <= Amount; i++) {
            Vec3d myPos = spreadPos(world, pos);

            world.spawnParticles(
                    effect,
                    myPos.getX(),
                    myPos.getY(),
                    myPos.getZ(),
                    1,
                    velocity3.getX(),
                    velocity3.getY(),
                    velocity3.getZ(),
                    0.0f
            );
        }
    };

    public static Vec3d spreadPos(World world, Vec3d origin) {
        Random rand = world.getRandom();

        return new Vec3d(
                origin.x + (rand.nextGaussian() * 0.3),
                origin.y + (rand.nextGaussian() * 0.3),
                origin.z + (rand.nextGaussian() * 0.3)
        );
    }
}
