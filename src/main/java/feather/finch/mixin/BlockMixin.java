package feather.finch.mixin;

import feather.finch.block.FoldAnchorBlock;
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
    /**
     * Делаем FoldAnchorBlock прозрачным для движка игры, 
     * чтобы через него можно было рендерить внутренности терема.
     */
    @Inject(method = "isTranslucent", at = @At("HEAD"), cancellable = true)
    private void featherFinch$anchorIsTranslucent(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if ((Object)state.getBlock() instanceof FoldAnchorBlock) {
            cir.setReturnValue(true);
        }
    }
}
