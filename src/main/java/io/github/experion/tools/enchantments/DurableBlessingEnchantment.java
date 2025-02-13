package io.github.experion.tools.enchantments;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record DurableBlessingEnchantment(EnchantmentLevelBasedValue amount) implements EnchantmentEntityEffect {
    public static final MapCodec<DurableBlessingEnchantment> CODEC = RecordCodecBuilder.mapCodec(inst ->
            inst.group(EnchantmentLevelBasedValue.CODEC.fieldOf("amount").forGetter(DurableBlessingEnchantment::amount)).apply(inst,DurableBlessingEnchantment::new)
            );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {}

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return null;
    }
}
