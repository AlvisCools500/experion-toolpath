package com.github.experion.tools.lib;

import net.minecraft.block.Block;
import net.minecraft.registry.tag.TagKey;

public class ToolVals {
    public static class Base {
        private final float attackDMG;
        private final float attackSPEED;

        public Base(float attackDMG, float attackSPEED) {
            this.attackDMG = attackDMG;
            this.attackSPEED = attackSPEED;
        }

        public float getAttackDMG() {
            return attackDMG;
        }

        public float getAttackSPEED() {
            return attackSPEED;
        }
    }

    public static class Mats {
        private final TagKey<Block> inverseTag;
        private final int itemDurability;
        private final float miningSpeed;
        private final float attackDamage;
        private final int enchantability;

        public Mats(TagKey<Block> inverseTag, int itemDurability, float miningSpeed, float attackDamage, int enchantability) {
            this.inverseTag = inverseTag;
            this.itemDurability = itemDurability;
            this.miningSpeed = miningSpeed;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
        }

        public TagKey<Block> getInverseTag() {
            return inverseTag;
        }

        public int getItemDurability() {
            return itemDurability;
        }

        public float getMiningSpeed() {
            return miningSpeed;
        }

        public float getAttackDamage() {
            return attackDamage;
        }

        public int getEnchantability() {
            return enchantability;
        }
    }
}
