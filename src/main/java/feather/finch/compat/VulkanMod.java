package feather.finch.compat;

public class VulkanMod {
    public static boolean isVulkanEnabled() {
        try {
            // Пытаемся найти реальный класс VulkanMod в рантайме
            Class.forName("net.vulkanmod.vulkan.Vulkan");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
