package com.github.experion.tools.initializer;

import com.github.experion.tools.items.tool_lambdas.GetLambdas;
import com.github.experion.tools.items.tool_lambdas.ToolLambdas;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class ModEvents {
    public static void init() {
        AttackBlockCallback.EVENT.register((playerEntity, world, hand, blockPos, direction) -> {

            if (hand == Hand.MAIN_HAND) {
                ItemStack stack = playerEntity.getStackInHand(hand);

                if (!stack.isEmpty()) {
                    Item item = stack.getItem();

                    if (item instanceof GetLambdas) {
                        ToolLambdas toolLambdas = ((GetLambdas) item).getLambdas();

                        if (toolLambdas.enable_punchblock) {
                            toolLambdas.lambdas.onPunchBlock(stack,world,blockPos,playerEntity);
                        }
                    }
                }
            }

            return ActionResult.PASS;
        });


    }
}
