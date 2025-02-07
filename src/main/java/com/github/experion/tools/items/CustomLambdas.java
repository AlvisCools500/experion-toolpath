package com.github.experion.tools.items;

import com.github.experion.tools.items.custom_lambdas.*;
import com.github.experion.tools.items.tool_lambdas.TriggerLambdas;

public class CustomLambdas {
    public static final TriggerLambdas NON_LAMBDAS = new NonLambdas();

    public static final TriggerLambdas FLINT_LAMBDAS = new FlintLambdas();
    public static final TriggerLambdas AZALEA_LAMBDAS = new AzaleaLambdas();
    public static final TriggerLambdas COPPER_LAMBDAS = new CopperLambdas();
    public static final TriggerLambdas VAMPIRE_STEEL_LAMBDAS = new VampireSteelLambdas();

    public static final TriggerLambdas REDSTONE_LAMBDAS = new RedstoneLambdas();

    public static void init() {}
}
