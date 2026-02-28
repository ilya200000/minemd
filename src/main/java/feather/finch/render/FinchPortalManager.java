package feather.finch.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.opengl.GL11;
import java.util.ArrayList;
import java.util.List;

public class FinchPortalManager {
    private static final List<PortalArea> activePortals = new ArrayList<>();
    private static boolean isRenderingInternal = false;

    public static void clearCache() {
        activePortals.clear();
    }

    public static void renderPortals(MatrixStack matrices, VertexConsumerProvider consumers) {
        if (activePortals.isEmpty() || isRenderingInternal) return;

        isRenderingInternal = true;
        RenderSystem.assertOnRenderThread();
        
        // Настройка Stencil Buffer для неевклидовых переходов
        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
        
        for (PortalArea portal : activePortals) {
            // Записываем маску входа
            GL11.glStencilFunc(GL11.GL_ALWAYS, 1, 0xFF);
            GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);
            GL11.glColorMask(false, false, false, false);
            GL11.glDepthMask(false);
            
            // Здесь должна быть логика отрисовки геометрии входа
            
            // Рендерим мир внутри маски
            GL11.glStencilFunc(GL11.GL_EQUAL, 1, 0xFF);
            GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_KEEP);
            GL11.glColorMask(true, true, true, true);
            GL11.glDepthMask(true);
        }

        GL11.glDisable(GL11.GL_STENCIL_TEST);
        isRenderingInternal = false;
    }

    public static record PortalArea(double x, double y, double z, float width, float height) {}
}


