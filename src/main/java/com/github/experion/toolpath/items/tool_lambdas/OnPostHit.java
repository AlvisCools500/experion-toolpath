package com.github.experion.toolpath.items.tool_lambdas;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface OnPostHit {
    void trigger(ItemStack stack, LivingEntity target, LivingEntity attacker);
}
