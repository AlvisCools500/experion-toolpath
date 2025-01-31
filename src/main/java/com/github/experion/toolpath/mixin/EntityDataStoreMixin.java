package com.github.experion.toolpath.mixin;

import com.github.experion.toolpath.lib.ImplyData;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityDataStoreMixin implements ImplyData {
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
}

/*
@Mixin(Entity.class)
public abstract class EntityDataStoreMixin implements ImplyData {
    private NbtCompound ToolPath_Data;
    private final String mypath = "experion_tool_path.datastore";

    @Override
    public NbtCompound getToolPathData() {
        if (this.ToolPath_Data == null) {
            this.ToolPath_Data = new NbtCompound();
        }
        return this.ToolPath_Data;
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    public void inject_writeNbt(NbtCompound nbt) {
        ModInit.LOGGER.info("Write NBT?");
        if (ToolPath_Data != null) {
            nbt.put(mypath, ToolPath_Data);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    public void inject_readNbt(NbtCompound nbt, CallbackInfo info) {
        ModInit.LOGGER.info("READ NBT?!");
        if (nbt.contains(mypath)) {
            ToolPath_Data = nbt.getCompound(mypath);
        }
    }
}*/
