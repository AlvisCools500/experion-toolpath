package io.github.experion.tools.item;

import io.github.experion.tools.item.custom_lambdas.*;
import io.github.experion.tools.item.tool_lambdas.TriggerLambdas;

public class CustomLambdas {
    public static final TriggerLambdas NON_LAMBDAS = new NonLambdas();

    public static final TriggerLambdas FLINT_LAMBDAS = new FlintLambdas();
    public static final TriggerLambdas AZALEA_LAMBDAS = new AzaleaLambdas();
    public static final TriggerLambdas COPPER_LAMBDAS = new CopperLambdas();
    public static final TriggerLambdas GILDED_IRON_LAMBDAS = new GildedIronLambdas();
    public static final TriggerLambdas VAMPIRE_STEEL_LAMBDAS = new VampireSteelLambdas();
    public static final TriggerLambdas FROSTED_STEEL_LAMBDAS = new FrostedSteelLambdas();
    public static final TriggerLambdas AQUAMOND_LAMBDAS = new AquamondLambdas();
    public static final TriggerLambdas OBSIDIAN_LAMBDAS = new ObsidianLambdas();
    public static final TriggerLambdas SOUL_STEEL_LAMBDAS = new SoulSteelLambdas();

    public static final TriggerLambdas REDSTONE_LAMBDAS = new RedstoneLambdas();

    public static void init() {}
}
