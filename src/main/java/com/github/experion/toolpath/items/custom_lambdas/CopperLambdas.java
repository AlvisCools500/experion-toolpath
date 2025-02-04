package com.github.experion.toolpath.items.custom_lambdas;

import com.github.experion.toolpath.initializer.ModDataComponents;
import com.github.experion.toolpath.items.tool_lambdas.ExistsLambdas;
import com.github.experion.toolpath.items.tool_lambdas.TriggerLambdas;
import com.github.experion.toolpath.lib.Experion.ExperionRegistery;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public class CopperLambdas implements TriggerLambdas {
    @Override
    public ExistsLambdas exists() {
        return new ExistsLambdas().PostMine().PostHit().Thundered().EditDamage().EditEffeciency();
    }

    private int check_status(ItemStack stack) {
        if (stack.contains(ModDataComponents.COPPER_STATUS)) {
            return stack.get(ModDataComponents.COPPER_STATUS);
        }else {
            stack.set(ModDataComponents.COPPER_STATUS,1);
        }

        return 1;
    }

    @Override
    public String getLambdasName() {
        return "Copper";
    }

    @Override
    public void onThundered(ItemStack stack, ServerWorld world, ServerPlayerEntity player) {
        stack.set(ModDataComponents.COPPER_STATUS,3);
    }


}
