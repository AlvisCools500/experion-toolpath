package com.github.experion.toolpath.client.item.properties.numeric;

import com.github.experion.toolpath.initializer.ModDataComponents;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.render.item.property.numeric.NumericProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class CopperToolStatusProperty implements NumericProperty {
    public static final MapCodec<CopperToolStatusProperty> CODEC = MapCodec.unit(CopperToolStatusProperty::new);

    @Override
    public float getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity holder, int seed) {
        if (stack.contains(ModDataComponents.COPPER_STATUS)) {
            int status = stack.get(ModDataComponents.COPPER_STATUS);
            if (status == 2) {
                return 2.0f;
            } else if (status == 3) {
                return 3.0f;
            }
        }
        return 1.0f;
    }

    @Override
    public MapCodec<? extends NumericProperty> getCodec() {
        return CODEC;
    }
}
