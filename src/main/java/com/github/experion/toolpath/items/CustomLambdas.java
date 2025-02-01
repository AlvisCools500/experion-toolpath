package com.github.experion.toolpath.items;

import com.github.experion.toolpath.items.custom_lambdas.AzaleaLambdas;
import com.github.experion.toolpath.items.custom_lambdas.FlintLambdas;
import com.github.experion.toolpath.items.custom_lambdas.NonLambdas;
import com.github.experion.toolpath.items.custom_lambdas.RedstoneLambdas;
import com.github.experion.toolpath.items.tool_lambdas.TriggerLambdas;

public class CustomLambdas {
    public static final TriggerLambdas NON_LAMBDAS = new NonLambdas();

    public static final TriggerLambdas FLINT_LAMBDAS = new FlintLambdas();
    public static final TriggerLambdas AZALEA_LAMBDAS = new AzaleaLambdas();
    public static final TriggerLambdas REDSTONE_LAMBDAS = new RedstoneLambdas();

    public static void init() {}
}
