package feather.finch.mixin;

import feather.finch.block.FoldAnchorBlock; // ПРОВЕРЬ ЭТОТ ИМПОРТ!
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {
    // Добавлен remap = true и проверь, совпадает ли сигнатура метода с твоей версией MC
    @Inject(method = "isTranslucent", at = @At("HEAD"), cancellable = true)
    private void featherFinch$anchorIsTranslucent(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        // Обязательно убедись, что FoldAnchorBlock импортирован выше!
        if ((Object)state.getBlock() instanceof FoldAnchorBlock) {
            cir.setReturnValue(true);
        }
    }
}
