package io.github.experion.tools.mixin;

import io.github.experion.tools.item.tool_lambdas.GetLambdas;
import io.github.experion.tools.item.tool_lambdas.ToolLambdas;
import io.github.experion.tools.item.tool_lambdas.TriggerLambdas;
import io.github.experion.tools.lib.ImplyData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(Entity.class)
public abstract class EntityMixin implements ImplyData {
    private final String mypath = "experion_tool_path.datastore";
    private NbtCompound persistentData;

    @Override
    public NbtCompound getToolPathData() {
        if(this.persistentData == null) {
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable info) {
        if(this.persistentData != null) {
            nbt.put(mypath, this.persistentData);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains(mypath, 10)) {
            this.persistentData = nbt.getCompound(mypath);
        }
    }

    @Inject(method = "onStruckByLightning", at = @At("TAIL"))
    private void lightningstruck(ServerWorld world, LightningEntity lightning, CallbackInfo info) {
        if (!world.isClient) {
            Entity entity = (Entity) (Object) this;

            if (entity instanceof PlayerEntity) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) entity;
                PlayerInventory inv = serverPlayer.getInventory();

                List<ItemStack> StackList = new ArrayList<>();

                for (int i = 0; i < serverPlayer.getInventory().size(); i++) {
                    ItemStack stack = serverPlayer.getInventory().getStack(i);
                    Item item = stack.getItem();

                    if (item instanceof GetLambdas) {
                        ToolLambdas toolLambdas = ((GetLambdas) item).getLambdas();

                        if (toolLambdas.getThundered) {
                            StackList.add(stack);
                        }
                    }
                }

                if (!StackList.isEmpty()) {
                    for (ItemStack stack : StackList) {
                        ToolLambdas toolLambdas = ((GetLambdas) stack.getItem()).getLambdas();
                        TriggerLambdas lambdas = toolLambdas.lambdas;

                        lambdas.onThundered(stack,world,serverPlayer);
                    }
                }

            }
        }

    }

}

