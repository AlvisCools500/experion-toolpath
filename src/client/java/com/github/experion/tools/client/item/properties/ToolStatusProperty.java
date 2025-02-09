package com.github.experion.tools.client.item.properties;

import com.github.experion.tools.initializer.ModDataComponents;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.render.item.property.numeric.NumericProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ToolStatusProperty implements NumericProperty {
    public static final MapCodec<ToolStatusProperty> CODEC = MapCodec.unit(ToolStatusProperty::new);

    @Override
    public float getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity holder, int seed) {
        if (stack.contains(ModDataComponents.TOOL_STATUS)) {
            int status = stack.get(ModDataComponents.TOOL_STATUS);
            return Math.max((float) stack.get(ModDataComponents.TOOL_STATUS), 1.0f);
        }
        return 1.0f;
    }

    @Override
    public MapCodec<? extends NumericProperty> getCodec() {
        return CODEC;
    }
}
