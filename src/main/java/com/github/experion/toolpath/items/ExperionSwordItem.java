package com.github.experion.toolpath.items;

import com.github.experion.toolpath.ModInit;
import com.github.experion.toolpath.items.tool_lambdas.GetLambdas;
import com.github.experion.toolpath.items.tool_lambdas.OnPostHit;
import com.github.experion.toolpath.items.tool_lambdas.ToolLambdas;
import com.github.experion.toolpath.items.tool_lambdas.ToolStaticTrigger;
import com.github.experion.toolpath.lib.ToolLib;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class ExperionSwordItem extends SwordItem implements GetLambdas {
    final ToolLambdas toolLamb;

    public ExperionSwordItem(ToolMaterial material, Item.Settings settings, ToolLambdas toolLambdas) {
        super(material, settings);
        this.toolLamb = toolLambdas;
        ToolLib.onAdded(this, ToolLib.ToolType.SWORD,this.toolLamb);
    }

    @Override
    public ToolLambdas getLambdas() {
        return this.toolLamb;
    }

    @Override
    public Text getName() {
        return this.toolLamb.lambdas.getName(super.getName());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        this.toolLamb.lambdas.appendTooltip(stack,context,tooltip,type);
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        float res = ToolStaticTrigger.getDamage(toolLamb,target,baseAttackDamage,damageSource,super.getBonusAttackDamage(target, baseAttackDamage, damageSource));
        return res;
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
