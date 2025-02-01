package com.github.experion.toolpath.client.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class AzaleaEffectParticle extends SpriteBillboardParticle {
    private final float startscale = 0.2f;
    private final float endscale = 0f;

    private final int MaxDur = 100;

    private float targ_angle;

    public AzaleaEffectParticle(ClientWorld clientWorld, double x, double y, double z, double vx, double vy, double vz, SpriteProvider spriteProvider) {
        super(clientWorld, x, y, z, vx, vy, vz);
        this.scale = startscale;
        this.maxAge = MaxDur;
        this.gravityStrength = 0.3f;
        this.velocityMultiplier = 1.0F;
        this.setSprite(spriteProvider);

        this.targ_angle = (float)Math.toRadians(this.random.nextBoolean() ? (double)-30.0F : (double)30.0F);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.age >= this.maxAge/2) {
            float halved = ((float) this.maxAge) / 2;
            float total_alpha = Math.clamp((((float) (this.age)) - halved) / halved, 0, 1);
            this.scale = startscale + (endscale - startscale) * total_alpha;
        }


        //float main_alpha = Math.clamp(((float) this.age) / ((float) this.maxAge),0,1);

        //this.angle = 0 + (0 - this.targ_angle) * main_alpha;

        this.prevAngle = this.angle;
        this.angle += this.targ_angle / 20.0F;



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
            return new AzaleaEffectParticle(world, x, y, z, dx, dy, dz, spriteProvider);
        }
    }

}
