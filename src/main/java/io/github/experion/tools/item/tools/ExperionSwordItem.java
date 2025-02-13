package io.github.experion.tools.item.tools;

import io.github.experion.tools.item.tool_lambdas.GetLambdas;
import io.github.experion.tools.item.tool_lambdas.ToolLambdas;
import io.github.experion.tools.item.tool_lambdas.ToolStaticTrigger;
import io.github.experion.tools.lib.ToolLib;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class ExperionSwordItem extends SwordItem implements GetLambdas {
    final ToolLambdas toolLamb;

    public ExperionSwordItem(ToolMaterial material, Item.Settings settings, ToolLambdas toolLambdas, float dmg, float speed) {
        super(material, dmg, speed, settings);
        this.toolLamb = toolLambdas;
        ToolLib.onAdded(this, ToolLib.ToolType.SWORD,this.toolLamb);
    }

    @Override
    public ToolLambdas getLambdas() {
        return this.toolLamb;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        return ToolStaticTrigger.OnUse(toolLamb,world,user,hand,super.use(world, user, hand));
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (toolLamb.enable_usagetick) {
            ToolStaticTrigger.usageTick(toolLamb,world,user,stack,remainingUseTicks);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (toolLamb.enable_inventorytick) {
            toolLamb.lambdas.inventroytick(stack,world,entity,slot,selected);
        }
    }

    @Override
    public Text getName(ItemStack stack) {
        return this.toolLamb.lambdas.getName(super.getName(stack), stack);
    }

    @Override
    public boolean canBeEnchantedWith(ItemStack stack, RegistryEntry<Enchantment> enchantment, EnchantingContext context) {
        return this.toolLamb.lambdas.canBeEnchanted(stack,enchantment,context,super.canBeEnchantedWith(stack, enchantment, context));
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
        boolean bool = super.postMine(stack,world,state,pos,miner);
        ToolStaticTrigger.PostMine(this.toolLamb,stack,world,state,pos,miner, bool);
        return bool;
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


}
