package com.github.experion.toolpath.items;

import com.github.experion.toolpath.items.tool_lambdas.GetLambdas;
import com.github.experion.toolpath.items.tool_lambdas.ToolLambdas;
import com.github.experion.toolpath.items.tool_lambdas.ToolStaticTrigger;
import com.github.experion.toolpath.lib.ToolLib;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExperionPickaxeItem extends PickaxeItem implements GetLambdas {
    final ToolLambdas toolLamb;

    public ExperionPickaxeItem(ToolMaterial material, Settings settings, ToolLambdas toollamb) {
        super(material, settings);
        this.toolLamb = toollamb;
        ToolLib.onAdded(this, ToolLib.ToolType.PICKAXE, this.toolLamb);
    }

    @Override
    public float getMiningSpeed(ItemStack stack, BlockState state) {
        return ToolStaticTrigger.getMiningSpeed(this.toolLamb,stack,state,super.getMiningSpeed(stack,state));
    }


    @Override
    public ToolLambdas getLambdas() {
        return this.toolLamb;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        super.postMine(stack,world,state,pos,miner);
        ToolStaticTrigger.PostMine(this.toolLamb,stack,world,state,pos,miner);
        return true;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postHit(stack,target,attacker);
        ToolStaticTrigger.PostHit(this.toolLamb,stack,target,attacker);
        return true;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return ToolStaticTrigger.OnUseBlock(this.toolLamb,context,super.useOnBlock(context));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ToolStaticTrigger.OnUse(toolLamb,world,user,hand,super.use(world, user, hand));
    }
}
