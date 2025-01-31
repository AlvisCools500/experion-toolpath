package com.github.experion.toolpath.mixin;

import com.github.experion.toolpath.ModInit;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MyMixin {
  @Inject(at = @At("HEAD"), method = "runServer")
  private void runServer(CallbackInfo info) {
    ModInit.LOGGER.info("Hello, World! (Common mixin)");


  }
}
