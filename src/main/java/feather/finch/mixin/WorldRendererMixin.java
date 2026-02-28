package feather.finch.mixin;

import feather.finch.render.FinchPortalManager;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {

    @Inject(
        method = "render", 
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;renderLayer(Lnet/minecraft/client/render/RenderLayer;Lnet/minecraft/client/util/math/MatrixStack;DDDLorg/joml/Matrix4f;)V", ordinal = 0)
    )
    private void featherFinch$renderPocketPortals(
        MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, 
        Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, 
        Matrix4f projectionMatrix, CallbackInfo ci
    ) {
        // Проверяем, активен ли VulkanMod
        // Если да — вызываем отрисовку внутренних миров через Vulkan Pipelines
        if (FinchPortalManager.shouldRender()) {
            FinchPortalManager.renderAllPortals(matrices, projectionMatrix, camera, tickDelta);
        }
    }
}
