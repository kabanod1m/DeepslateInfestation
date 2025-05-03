package org.winglessbirds.deepslateinfestation.util;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.LinkedList;

public class BlocksAroundFinder {

    public static LinkedList<BlockPos> getBlocksAround (World world, BlockPos pos, LinkedList<Block> searchFor) {
        LinkedList<BlockPos> toReturn = new LinkedList<>();
        LinkedList<BlockPos> allSurrounding = new LinkedList<>();
        allSurrounding.add(pos.north());
        allSurrounding.add(pos.east());
        allSurrounding.add(pos.south());
        allSurrounding.add(pos.west());
        allSurrounding.add(pos.up());
        allSurrounding.add(pos.down());

        for (BlockPos posSurround : allSurrounding) {
            Block blockSurround = world.getBlockState(posSurround).getBlock();
            for (Block searchBlock : searchFor) {
                if (blockSurround.equals(searchBlock)) {
                    toReturn.add(posSurround);
                    break;
                }
            }
        }

        return toReturn;
    }

}
