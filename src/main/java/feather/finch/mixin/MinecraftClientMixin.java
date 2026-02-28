package feather.finch.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "setWorld", at = @At("HEAD"))
    private void featherFinch$onWorldChange(ClientWorld world, CallbackInfo ci) {
        // Очищаем кэш порталов при смене дименшна, чтобы не было "фантомных" окон
        FinchPortalManager.clearCache();
    }
}
