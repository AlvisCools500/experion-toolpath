package io.github.ExperionPlanet.tools.client.particles;

import io.github.ExperionPlanet.tools.item.EasingService;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class LifestealParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;
    private final double targY;
    private final double startY;

    protected LifestealParticle(ClientWorld clientWorld, double x, double y, double z, double vx, double vy, double vz, SpriteProvider spriteProvider) {
        super(clientWorld, x, y, z, vx, vy, vz);
        this.maxAge = clientWorld.getRandom().nextBetween(10,15);
        this.scale = 0.5f;
        this.spriteProvider = spriteProvider;
        this.setSpriteForAge(spriteProvider);

        this.targY = y + 1;
        this.startY = y;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;

        if (this.age++ >= this.maxAge) {
            this.markDead();
        } else {
            this.setSpriteForAge(this.spriteProvider);

            double AgeIn = this.age;
            double AgeOut = this.maxAge;

            double alpha = AgeIn/AgeOut;
            this.y = this.startY + (this.targY - this.startY) * EasingService.OutBack(alpha);
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z, double dx, double dy, double dz) {
            return new LifestealParticle(world, x, y, z, dx, dy, dz, spriteProvider);
        }
    }
}
