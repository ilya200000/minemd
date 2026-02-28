package feather.finch.mixin;

import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderLayer.class)
public class RenderLayerMixin {
    @Inject(method = "getTranslucent", at = @At("HEAD"), cancellable = true)
    private static void featherFinch$makePortalsTranslucent(CallbackInfoReturnable<RenderLayer> cir) {
        // Мы заставляем движок считать блоки портала всегда транспарентными,
        // чтобы VulkanMod не выбрасывал их из очереди отрисовки (Depth Culling)
        if (FinchGlobalFlags.isRenderingPocket()) {
            cir.setReturnValue(RenderLayer.getTranslucent());
        }
    }
}
