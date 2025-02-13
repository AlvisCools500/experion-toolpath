package io.github.experion.tools.client.item.properties;

import io.github.experion.tools.initializer.ModDataComponents;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.render.item.property.bool.BooleanProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ModelTransformationMode;
import org.jetbrains.annotations.Nullable;

public class FlintCriticalProperty implements BooleanProperty {
    public static final MapCodec<FlintCriticalProperty> CODEC = MapCodec.unit(FlintCriticalProperty::new);

    @Override
    public boolean getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user, int seed, ModelTransformationMode modelTransformationMode) {
        if (stack.contains(ModDataComponents.FLINT_AMOUNTCRITICAL)) {
            return stack.get(ModDataComponents.FLINT_AMOUNTCRITICAL) > 0;
        }

        return false;
    }

    @Override
    public MapCodec<? extends BooleanProperty> getCodec() {
        return CODEC;
    }
}
