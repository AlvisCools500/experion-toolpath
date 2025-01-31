package com.github.experion.toolpath.items;

import com.github.experion.toolpath.ModInit;
import com.github.experion.toolpath.lib.PlayerDataStore;
import com.github.experion.toolpath.misc.payloads.DiscoveryPayLoad;
import com.github.experion.toolpath.misc.payloads.PathDataPayLoad;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtInt;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ToolDeveloper extends Item {
    public ToolDeveloper(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;

            ServerPlayNetworking.send(serverPlayer,new DiscoveryPayLoad(new Random().nextInt(1,2), Registries.ITEM.getId(this)));
        }
        /*
        if (!world.isClient()) {
            List<Integer> list = PlayerDataStore.get_ToolPathList(user);
            if (user.isSneaking()) {
                int res = world.getRandom().nextBetween(1, 100);

                if (!list.contains(res)) {
                    PlayerDataStore.add_NbtList(user,PlayerDataStore.NbtKeys.TOOL_PATH, NbtElement.INT_TYPE, NbtInt.of(res));
                }else {
                    user.sendMessage(Text.literal("already exists? What").formatted(Formatting.RED).formatted(Formatting.BOLD));
                }
            }else {
                if (list.isEmpty()) {
                    user.sendMessage(Text.literal("Empty...").formatted(Formatting.RED).formatted(Formatting.BOLD));
                }else {
                    for (int i : list) {
                        user.sendMessage(Text.literal("read: " + i));
                    }
                }
            }
        }*/

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
