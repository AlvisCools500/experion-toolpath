package io.github.ExperionPlanet.tools.client.item.properties;

import com.mojang.serialization.MapCodec;
import io.github.ExperionPlanet.tools.initializer.ModDataComponents;
import net.minecraft.client.render.item.property.bool.BooleanProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ModelTransformationMode;
import org.jetbrains.annotations.Nullable;

public class HasAmountProperty implements BooleanProperty {
    public static final MapCodec<HasAmountProperty> CODEC = MapCodec.unit(HasAmountProperty::new);

    @Override
    public boolean getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user, int seed, ModelTransformationMode modelTransformationMode) {
        if (stack.contains(ModDataComponents.TOOL_COUNTER_FIRST)) {
            return stack.get(ModDataComponents.TOOL_COUNTER_FIRST) > 0;
        }

        return false;
    }

    @Override
    public MapCodec<? extends BooleanProperty> getCodec() {
        return CODEC;
    }
}
