package com.github.experion.toolpath.items.tool_lambdas;

import com.github.experion.toolpath.lib.ToolLib;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public interface ToolMainTrigger {
    void trigger(ItemStack stack, World world, Vec3d pos, LivingEntity plr, ToolLib.TriggerType trigType);
}
