package org.winglessbirds.deepslateinfestation.entity.mob;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;
import org.winglessbirds.deepslateinfestation.DeepslateInfestation;
import org.winglessbirds.deepslateinfestation.block.Blocks;
import org.winglessbirds.deepslateinfestation.block.DeepslateInfestedBlock;
import net.minecraft.entity.EntityType;
import org.winglessbirds.deepslateinfestation.util.BlocksAroundFinder;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

public class DeepslateSilverfishEntity extends HostileEntity {

    public static final Predicate<LivingEntity> ATTACK_PREDICATE = (entity) -> {
        EntityType<?> entityType = entity.getType();
        return entityType == EntityType.ZOMBIE || entityType == EntityType.HUSK || entityType == EntityType.DROWNED;
    };
    @Nullable
    private CallForHelpGoal callForHelpGoal;
    public boolean sawPlayer = false;

    public DeepslateSilverfishEntity (EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals () {
        this.callForHelpGoal = new CallForHelpGoal(this);

        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(1, new PowderSnowJumpGoal(this, this.getWorld()));
        this.goalSelector.add(3, this.callForHelpGoal);
        this.goalSelector.add(4, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.add(5, new WanderAndInfestGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this, new Class[0]).setGroupRevenge(new Class[0]));
        this.targetSelector.add(2, new ActiveTargetGoal<PlayerEntity>((MobEntity)this, PlayerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>((MobEntity)this, HostileEntity.class, 10, true, false, ATTACK_PREDICATE));
    }

    @Override
    public double getHeightOffset () {
        return 0.1;
    }

    @Override
    protected float getActiveEyeHeight (EntityPose pose, EntityDimensions dimensions) {
        return 0.1f;
    }

    public static DefaultAttributeContainer.Builder createDeepslateSilverfishAttributes () {
        return HostileEntity.createHostileAttributes().
                add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0).
                add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15).
                add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0);
    }

    @Override
    protected MoveEffect getMoveEffect () {
        return MoveEffect.EVENTS;
    }

    @Override
    protected SoundEvent getAmbientSound () {
        return SoundEvents.ENTITY_SILVERFISH_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound (DamageSource source) {
        return SoundEvents.ENTITY_SILVERFISH_HURT;
    }

    @Override
    protected SoundEvent getDeathSound () {
        return SoundEvents.ENTITY_SILVERFISH_DEATH;
    }

    @Override
    protected void playStepSound (BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_SILVERFISH_STEP, 0.15f, 1.0f);
    }

    @Override
    public boolean damage (DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        }
        if ((source.getAttacker() instanceof PlayerEntity || source.isIn(DamageTypeTags.ALWAYS_TRIGGERS_SILVERFISH)) && this.callForHelpGoal != null) {
            this.callForHelpGoal.onHurt();
        }
        return super.damage(source, amount);
    }

    @Override
    public void tick () {
        super.tick();
        if (!sawPlayer && this.getTarget() instanceof PlayerEntity) {
            sawPlayer = true;
        }
    }

    @Override
    public float getPathfindingFavor (BlockPos pos, WorldView world) {
        if (DeepslateInfestedBlock.isInfestable(world.getBlockState(pos.down()))) {
            return 10.0f;
        }
        return super.getPathfindingFavor(pos, world);
    }

    public static boolean isSpawnDark (ServerWorldAccess world, BlockPos pos, Random random) {
        if (world.getLightLevel(LightType.SKY, pos) != 0) {
            return false;
        }

        return world.getLightLevel(LightType.BLOCK, pos) <= DeepslateInfestation.CFG.spawnLightLevelMax;
    }

    public static boolean canSpawn (EntityType<? extends HostileEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        LinkedList<Block> considerDeepslate = new LinkedList<>();
        considerDeepslate.add(net.minecraft.block.Blocks.DEEPSLATE);
        considerDeepslate.add(net.minecraft.block.Blocks.COBBLED_DEEPSLATE);
        considerDeepslate.add(Blocks.DEEPSLATE_INFESTED_DEEPSLATE);
        considerDeepslate.add(Blocks.DEEPSLATE_INFESTED_COBBLED_DEEPSLATE);
        return world.getDifficulty() != Difficulty.PEACEFUL &&
               isSpawnDark(world, pos, random) &&
               (spawnReason == SpawnReason.SPAWNER || pos.getY() <= DeepslateInfestation.CFG.yLevelMax) &&
               ((spawnReason == SpawnReason.SPAWNER && HostileEntity.canMobSpawn(type, world, spawnReason, pos, random))
                || !BlocksAroundFinder.getBlocksAround((World)world.toServerWorld(), pos, considerDeepslate).isEmpty());
    }

    @Override
    public EntityGroup getGroup () {
        return EntityGroup.ARTHROPOD;
    }

    static class CallForHelpGoal extends Goal {

        private final DeepslateSilverfishEntity deepslateSilverfish;
        private int delay;

        public CallForHelpGoal (DeepslateSilverfishEntity deepslateSilverfish) {
            this.deepslateSilverfish = deepslateSilverfish;
        }

        public void onHurt () {
            if (this.delay == 0) {
                this.delay = this.getTickCount(20);
            }
        }

        @Override
        public boolean canStart () {
            return this.delay > 0;
        }

        @Override
        public void tick () {
            --this.delay;
            if (this.delay <= 0) {
                World world = this.deepslateSilverfish.getWorld();
                Random random = this.deepslateSilverfish.getRandom();
                BlockPos blockPos = this.deepslateSilverfish.getBlockPos();
                int i = 0;
                block0: while (i <= 5 && i >= -5) {
                    int j = 0;
                    while (j <= 10 && j >= -10) {
                        int k = 0;
                        while (k <= 10 && k >= -10) {
                            BlockPos blockPos2 = blockPos.add(j, i, k);
                            BlockState blockState = world.getBlockState(blockPos2);
                            Block block = blockState.getBlock();
                            if (block instanceof DeepslateInfestedBlock) {
                                if (world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                                    LinkedList<Block> considerAir = new LinkedList<>();
                                    considerAir.add(net.minecraft.block.Blocks.AIR); // noTODO: hardcoded air. maybe there is a vanilla air tag? // answer: there doesn't seem to be.
                                    considerAir.add(net.minecraft.block.Blocks.CAVE_AIR); // also air blocks must be added in most used descending order to reduce iterations
                                    LinkedList<BlockPos> airBlocksAround = BlocksAroundFinder.getBlocksAround(world, blockPos2, considerAir);
                                    Iterator<BlockPos> airBlocksAroundi = airBlocksAround.iterator();
                                    BlockPos closest = null;
                                    if (airBlocksAroundi.hasNext()) {
                                        closest = airBlocksAroundi.next();
                                    }
                                    while (airBlocksAroundi.hasNext()) {
                                        BlockPos current = airBlocksAroundi.next();
                                        if (current.getSquaredDistance(blockPos) < closest.getSquaredDistance(blockPos)) {
                                            closest = current;
                                        }
                                    }
                                    if (closest == null) {
                                        k = (k <= 0 ? 1 : 0) - k;
                                        continue;
                                    }

                                    // different block logic
                                    if (block.equals(Blocks.DEEPSLATE_INFESTED_DEEPSLATE)) {
                                        world.syncWorldEvent(2001, blockPos2, Block.getRawIdFromState(world.getBlockState(blockPos2))); // 2001 is the ID of Block break + Block break sound network packet. Looked it up in TurtleEntity TODO: get it from a packet class or something instead of using a raw numeric value
                                        if (random.nextBoolean()) { // 50% chance of Deepslate turning into Cobbled Deepslate
                                            world.setBlockState(blockPos2, net.minecraft.block.Blocks.COBBLED_DEEPSLATE.getDefaultState());
                                        } else {
                                            world.setBlockState(blockPos2, ((DeepslateInfestedBlock)block).toRegularState(world.getBlockState(blockPos2)));
                                        }
                                        DeepslateInfestedBlock.spawnDeepslateSilverfish((ServerWorld)world, closest, new Vec2f(0.0f, 0.0f), true);
                                    }
                                    if (block.equals(Blocks.DEEPSLATE_INFESTED_COBBLED_DEEPSLATE)) {
                                        if (random.nextInt(9) == 0) { // 10% chance of breaking Cobbled Deepslate
                                            world.breakBlock(blockPos2, true, this.deepslateSilverfish);
                                        } else {
                                            world.syncWorldEvent(2001, blockPos2, Block.getRawIdFromState(world.getBlockState(blockPos2))); // 2001 is the ID of Block break + Block break sound network packet. Looked it up in TurtleEntity TODO: get it from a packet class or something instead of using a raw numeric value
                                            DeepslateInfestedBlock.spawnDeepslateSilverfish((ServerWorld)world, closest, new Vec2f(0.0f, 0.0f), true);
                                            world.setBlockState(blockPos2, ((DeepslateInfestedBlock)block).toRegularState(world.getBlockState(blockPos2)));
                                        }
                                    }
                                } else {
                                    world.setBlockState(blockPos2, ((DeepslateInfestedBlock)block).toRegularState(world.getBlockState(blockPos2)), Block.NOTIFY_ALL);
                                }
                                if (random.nextBoolean()) break block0;
                            }
                            k = (k <= 0 ? 1 : 0) - k;
                        }
                        j = (j <= 0 ? 1 : 0) - j;
                    }
                    i = (i <= 0 ? 1 : 0) - i;
                }
            }
        }

    }

    static class WanderAndInfestGoal extends WanderAroundGoal {

        @Nullable
        private Direction direction;
        private boolean canInfest;

        public WanderAndInfestGoal (DeepslateSilverfishEntity deepslateSilverfish) {
            super(deepslateSilverfish, 1.0, 10);
            this.setControls(EnumSet.of(Control.MOVE));
        }

        @Override
        public boolean canStart () {
            if (this.mob.getTarget() != null) {
                return false;
            }
            if (!this.mob.getNavigation().isIdle()) {
                return false;
            }
            Random random = this.mob.getRandom();
            if (this.mob.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) && random.nextInt(toGoalTicks(10)) == 0) {
                this.direction = Direction.random(random);
                BlockPos blockPos = BlockPos.ofFloored(this.mob.getX(), this.mob.getY() + 0.5, this.mob.getZ()).offset(this.direction);
                BlockState blockState = this.mob.getWorld().getBlockState(blockPos);
                if (DeepslateInfestedBlock.isInfestable(blockState)) {
                    this.canInfest = true;
                    return true;
                }
            }
            this.canInfest = false;
            return super.canStart();
        }

        @Override
        public boolean shouldContinue () {
            if (this.canInfest) {
                return false;
            }
            return super.shouldContinue();
        }

        @Override
        public void start () {
            BlockPos blockPos;
            if ((this.mob instanceof DeepslateSilverfishEntity && !((DeepslateSilverfishEntity)this.mob).sawPlayer) || !this.canInfest) {
                super.start();
                return;
            }
            World worldAccess = this.mob.getWorld();
            BlockState blockState = worldAccess.getBlockState(blockPos = BlockPos.ofFloored(this.mob.getX(), this.mob.getY() + 0.5, this.mob.getZ()).offset(this.direction));
            if (DeepslateInfestedBlock.isInfestable(blockState)) {
                worldAccess.setBlockState(blockPos, DeepslateInfestedBlock.fromRegularState(blockState), Block.NOTIFY_ALL);
                this.mob.playSpawnEffects();
                this.mob.discard();
            }
        }

    }

}
