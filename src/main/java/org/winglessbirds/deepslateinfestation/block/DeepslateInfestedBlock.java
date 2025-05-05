package org.winglessbirds.deepslateinfestation.block;

import com.google.common.collect.Maps;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import org.winglessbirds.deepslateinfestation.entity.EntityType;
import org.winglessbirds.deepslateinfestation.entity.mob.DeepslateSilverfishEntity;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;

public class DeepslateInfestedBlock extends Block {

    private final Block regularBlock;
    private static final Map<Block, Block> REGULAR_TO_INFESTED_BLOCK = Maps.newIdentityHashMap();
    private static final Map<BlockState, BlockState> REGULAR_TO_INFESTED_STATE = Maps.newIdentityHashMap();
    private static final Map<BlockState, BlockState> INFESTED_TO_REGULAR_STATE = Maps.newIdentityHashMap();

    public DeepslateInfestedBlock (Block regularBlock, AbstractBlock.Settings settings) {
        super(settings.hardness(regularBlock.getHardness() / 1.5F).resistance(0.75F));
        this.regularBlock = regularBlock;
        REGULAR_TO_INFESTED_BLOCK.put(regularBlock, this);
    }

    public Block getRegularBlock () {
        return this.regularBlock;
    }

    public static boolean isInfestable (BlockState block) {
        return REGULAR_TO_INFESTED_BLOCK.containsKey(block.getBlock());
    }

    public void spawnDeepslateSilverfishFromBlock (ServerWorld world, BlockPos blockPos) {
        spawnDeepslateSilverfish(
                world,
                new Vec3d(blockPos.getX() + 0.5f, blockPos.getY(), blockPos.getZ() + 0.5f),
                new Vec2f(0.0f, 0.0f),
                false
        );
    }

    public static void spawnDeepslateSilverfish (ServerWorld world, BlockPos pos, Vec2f rot, boolean canInfestWithoutSeeingPLayer) {
        spawnDeepslateSilverfish(
                world,
                new Vec3d(pos.getX() + 0.5f, pos.getY(), pos.getZ() + 0.5f),
                rot,
                canInfestWithoutSeeingPLayer
        );
    }

    public static void spawnDeepslateSilverfish (ServerWorld world, Vec3d pos, Vec2f rot, boolean canInfestWithoutSeeingPLayer) {
        DeepslateSilverfishEntity deepslateSilverfishEntity = (DeepslateSilverfishEntity)EntityType.DEEPSLATE_SILVERFISH.create(world);
        if (deepslateSilverfishEntity == null) {
            return;
        }
        deepslateSilverfishEntity.refreshPositionAndAngles((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), rot.x, rot.y);
        world.spawnEntity(deepslateSilverfishEntity);
        deepslateSilverfishEntity.playSpawnEffects();
        if (canInfestWithoutSeeingPLayer) {
            deepslateSilverfishEntity.setCanInfest(true);
        }
    }

    @Override
    public void onStacksDropped (BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience) {
        super.onStacksDropped(state, world, pos, tool, dropExperience);
        if (world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS) && EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, tool) == 0) {
            this.spawnDeepslateSilverfishFromBlock(world, pos);
        }
    }

    public static BlockState fromRegularState (BlockState regularState) {
        return copyProperties(REGULAR_TO_INFESTED_STATE, regularState, () -> {
            return ((Block)REGULAR_TO_INFESTED_BLOCK.get(regularState.getBlock())).getDefaultState();
        });
    }

    public BlockState toRegularState (BlockState infestedState) {
        return copyProperties(INFESTED_TO_REGULAR_STATE, infestedState, () -> {
            return this.getRegularBlock().getDefaultState();
        });
    }

    private static BlockState copyProperties (Map<BlockState, BlockState> stateMap, BlockState fromState, Supplier<BlockState> toStateSupplier) {
        return (BlockState)stateMap.computeIfAbsent(fromState, (infestedState) -> {
            BlockState blockState = (BlockState)toStateSupplier.get();

            Property property;
            for (Iterator var3 = infestedState.getProperties().iterator(); var3.hasNext(); blockState = blockState.contains(property) ? (BlockState)blockState.with(property, infestedState.get(property)) : blockState) {
                property = (Property)var3.next();
            }

            return blockState;
        });
    }

}
