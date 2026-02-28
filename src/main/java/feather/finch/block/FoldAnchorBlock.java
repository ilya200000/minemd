package feather.finch.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

/**
 * Блок-якорь для 4D терема. 
 * Служит точкой привязки для неевклидового пространства.
 */
public class FoldAnchorBlock extends Block {
    public FoldAnchorBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Отрисовка формы блока (чуть меньше стандартного куба для визуального отличия)
        return VoxelShapes.cuboid(0.0625, 0.0, 0.0625, 0.9375, 1.0, 0.9375);
    }

    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0f; // Чтобы блок не отбрасывал тени внутрь портала
    }
}
