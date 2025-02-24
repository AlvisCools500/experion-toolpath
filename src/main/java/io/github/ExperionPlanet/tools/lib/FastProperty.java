package io.github.ExperionPlanet.tools.lib;

import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;

public class FastProperty {
    public static int intVal(ItemStack stack, ComponentType<Integer> component, int def_val) {
        if (stack.contains(component)) {
            return stack.get(component);
        }
        return def_val;
    }

    public static long longVal(ItemStack stack, ComponentType<Long> component, long def_val) {
        if (stack.contains(component)) {
            return stack.get(component);
        }
        return def_val;
    }

    public static boolean boolVal(ItemStack stack, ComponentType<Boolean> component, boolean def_val) {
        if (stack.contains(component)) {
            return stack.get(component);
        }
        return def_val;
    }


}
