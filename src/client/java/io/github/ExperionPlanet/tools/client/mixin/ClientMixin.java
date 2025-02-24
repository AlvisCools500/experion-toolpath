package io.github.ExperionPlanet.tools.client.mixin;

import io.github.ExperionPlanet.tools.ModInit;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class ClientMixin {
  @Inject(at = @At("HEAD"), method = "run")
  private void run(CallbackInfo info) {
    ModInit.LOGGER.info("Hello, World! (Client mixin)");
  }
}
