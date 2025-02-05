package com.github.experion.toolpath.initializer;

import com.github.experion.toolpath.ModInit;
import com.github.experion.toolpath.items.tool_identify.ToolIdList;
import com.github.experion.toolpath.items.tool_lambdas.GetLambdas;
import com.github.experion.toolpath.items.tool_lambdas.ToolLambdas;
import com.github.experion.toolpath.misc.persistent_state.ToolPathData;
import com.github.experion.toolpath.misc.persistent_state.ToolPathDataManager;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class ModEvents {
    public static void init() {
        ServerLifecycleEvents.SERVER_STARTED.register((server) -> {
            ToolPathData state = ToolPathDataManager.getOrCreate(server);

            if (state.getMain_map().isEmpty()) {
                ModInit.LOGGER.info("not available so generating...");
            }else {
                ModInit.LOGGER.info("It's already available!");
            }

            ToolIdList.init();
        });


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
                    }else {
                        ModInit.LOGGER.info("NOPE");
                    }
                }
            }

            return ActionResult.PASS;
        });


    }
}
