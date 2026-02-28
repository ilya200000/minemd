package feather.finch.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class FinchPortalManager {
    // Список позиций, где стоят "якоря" для входа в терем
    private static final List<PortalArea> activePortals = new ArrayList<>();
    private static boolean isRenderingInternal = false;

    public static void clearCache() {
        activePortals.clear();
    }

    public static void addPortal(PortalArea area) {
        if (!activePortals.contains(area)) {
            activePortals.add(area);
        }
    }

    // Основная магия отрисовки через трафарет (Stencil)
    public static void renderPortals(MatrixStack matrices, VertexConsumerProvider consumers) {
        if (activePortals.isEmpty() || isRenderingInternal) return;

        isRenderingInternal = true;
        
        // Включаем Stencil Buffer для "вырезания" входа
        RenderSystem.assertOnRenderThread();
        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
        
        for (PortalArea portal : activePortals) {
            // 1. Записываем маску в буфер (рисуем невидимый прямоугольник входа)
            GL11.glStencilFunc(GL11.GL_ALWAYS, 1, 0xFF);
            GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);
            GL11.glColorMask(false, false, false, false);
            GL11.glDepthMask(false);
            
            drawPortalFrame(portal, matrices);

            // 2. Рендерим внутренности только там, где маска = 1
            GL11.glStencilFunc(GL11.GL_EQUAL, 1, 0xFF);
            GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_KEEP);
            GL11.glColorMask(true, true, true, true);
            GL11.glDepthMask(true);
            
            // Здесь вызывается рендер "внутреннего" терема
            renderInnerWorld(portal, matrices);
        }

        GL11.glDisable(GL11.GL_STENCIL_TEST);
        isRenderingInternal = false;
    }

    private static void drawPortalFrame(PortalArea portal, MatrixStack matrices) {
        // Логика отрисовки полигона входа (двери терема)
    }

    private static void renderInnerWorld(PortalArea portal, MatrixStack matrices) {
        // Тут подменяем матрицу вида и рисуем терем по координатам портала
        // Это создает эффект "вложенности"
    }

    // Вспомогательный класс для координат
    public static record PortalArea(double x, double y, double z, float width, float height) {}
}
