package org.winglessbirds.deepslateinfestation.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;

public class RotatedDeepslateInfestedBlock extends DeepslateInfestedBlock {

    public RotatedDeepslateInfestedBlock(Block block, AbstractBlock.Settings settings) {
        super(block, settings);
        this.setDefaultState((BlockState)this.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.Y));
    }

    public BlockState rotate (BlockState state, BlockRotation rotation) {
        return PillarBlock.changeRotation(state, rotation);
    }

    protected void appendProperties (StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{PillarBlock.AXIS});
    }

    public BlockState getPlacementState (ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(PillarBlock.AXIS, ctx.getSide().getAxis());
    }

}
