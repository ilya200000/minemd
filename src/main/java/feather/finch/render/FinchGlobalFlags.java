package feather.finch.render;

public class FinchGlobalFlags {
    private static boolean renderingPocket = false;

    public static boolean isRenderingPocket() {
        return renderingPocket;
    }

    public static void setRenderingPocket(boolean value) {
        renderingPocket = value;
    }
}
