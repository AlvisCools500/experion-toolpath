package io.github.experion.tools.client.particles;

import io.github.experion.tools.item.EasingService;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class BubblesPopParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    private final float sizeStart = 0.3f;
    private final float sizeEnd = 0.75f;

    protected BubblesPopParticle(ClientWorld clientWorld, double x, double y, double z, double vx, double vy, double vz, SpriteProvider spriteProvider) {
        super(clientWorld, x, y, z, vx, vy, vz);
        this.maxAge = 20;
        this.scale = 0.5f;
        this.spriteProvider = spriteProvider;
        this.setSpriteForAge(spriteProvider);
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

            double alpha = Math.min(AgeIn/AgeOut, 1);

            this.scale = this.sizeStart + (this.sizeEnd - this.sizeStart) * ((float) EasingService.OutBack(alpha));
        }

    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z, double dx, double dy, double dz) {
            return new BubblesPopParticle(world, x, y, z, dx, dy, dz, spriteProvider);
        }
    }
}
