package net.bayruby.multiblock_extra;

import net.bayruby.multiblock_extra.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class MultiblockSpawner {

    private static final String UMVUTHI_ID = "mowziesmobs:umvuthi";
    private static final String FROSTMAW_ID = "mowziesmobs:frostmaw";
    private static final String WROUGHTNAUT_ID = "mowziesmobs:ferrous_wroughtnaut";

    public static void tryTrigger(Level level, BlockPos headPos, Block headBlock) {
        if (headBlock == ModBlocks.UMVUTHI_SKULL.get()) {
            if (checkIronGolemShape(level, headPos, Blocks.GOLD_BLOCK, ModBlocks.UMVUTHI_SKULL.get())) {
                MultiblockExtra.LOGGER.info("[MultiblockExtra] Umvuthi structure detected at {}", headPos);
                spawnMowziesMob(level, headPos.below(), UMVUTHI_ID);
                removeIronGolemShape(level, headPos);
                return;
            }

        } else if (headBlock == ModBlocks.RUSTED_HELM.get()) {
            if (checkIronGolemShape(level, headPos, Blocks.IRON_BLOCK, ModBlocks.RUSTED_HELM.get())) {
                MultiblockExtra.LOGGER.info("[MultiblockExtra] Wroughtnaut structure detected at {}", headPos);
                spawnMowziesMob(level, headPos.below(), WROUGHTNAUT_ID);
                removeIronGolemShape(level, headPos);
            }
        }
        else if (headBlock == ModBlocks.ICEBOUND.get()) {
            if (checkIronGolemShape(level, headPos, Blocks.SNOW_BLOCK, ModBlocks.ICEBOUND.get())) {
                MultiblockExtra.LOGGER.info("[MultiblockExtra] Frostmaw structure detected at {}", headPos);
                spawnMowziesMob(level, headPos.below(), FROSTMAW_ID);
                removeIronGolemShape(level, headPos);
                return;
            }
        }
    }

    /**
     * Iron golem‑style shape (4 body blocks + head):
     *
     * Orientation 1 (east–west arms):
     *   [ ] [C] [ ]
     *       |
     *      [L]
     *
     * C = center (body block, directly under head)
     * L = leg (body block, below center)
     * Arms = east & west of center
     *
     * Orientation 2 (north–south arms) is also allowed.
     */
    private static boolean checkIronGolemShape(Level level, BlockPos headPos, Block bodyBlock, Block headBlock) {
        // Head must match
        if (level.getBlockState(headPos).getBlock() != headBlock) {
            return false;
        }

        BlockPos center = headPos.below();
        BlockPos leg = center.below();

        // Center and leg
        if (level.getBlockState(center).getBlock() != bodyBlock) return false;
        if (level.getBlockState(leg).getBlock() != bodyBlock) return false;

        // Orientation 1: arms east–west
        boolean eastWest =
                level.getBlockState(center.east()).getBlock() == bodyBlock &&
                        level.getBlockState(center.west()).getBlock() == bodyBlock;

        // Orientation 2: arms north–south
        boolean northSouth =
                level.getBlockState(center.north()).getBlock() == bodyBlock &&
                        level.getBlockState(center.south()).getBlock() == bodyBlock;

        boolean match = eastWest || northSouth;

        if (!match) {
            MultiblockExtra.LOGGER.debug(
                    "[MultiblockExtra] Shape check failed at {} for body {} (EW={}, NS={})",
                    headPos, bodyBlock, eastWest, northSouth
            );
        }

        return match;
    }

    private static void removeIronGolemShape(Level level, BlockPos headPos) {
        BlockPos center = headPos.below();
        BlockPos leg = center.below();

        // Remove head
        level.destroyBlock(headPos, false);

        // Remove center and leg
        level.destroyBlock(center, false);
        level.destroyBlock(leg, false);

        // Remove both possible arm orientations (safe even if air)
        level.destroyBlock(center.east(), false);
        level.destroyBlock(center.west(), false);
        level.destroyBlock(center.north(), false);
        level.destroyBlock(center.south(), false);
    }

    public static void spawnMowziesMob(Level level, BlockPos centerPos, String mobId) {
        if (level.isClientSide) return;

        ResourceLocation id = ResourceLocation.parse(mobId);
        EntityType<?> type = BuiltInRegistries.ENTITY_TYPE.get(id);

        if (type == null) {
            MultiblockExtra.LOGGER.error("[MultiblockExtra] Unknown entity type: {}", mobId);
            return;
        }

        var entity = type.create(level);
        if (entity == null) {
            MultiblockExtra.LOGGER.error("[MultiblockExtra] Failed to create entity: {}", mobId);
            return;
        }

        // Spawn just above the center of the body
        entity.moveTo(
                centerPos.getX() + 0.5,
                centerPos.getY() + 1.0,
                centerPos.getZ() + 0.5,
                0.0F,
                0.0F
        );

        level.addFreshEntity(entity);
        MultiblockExtra.LOGGER.info("[MultiblockExtra] Spawned {} at {}", mobId, centerPos);
    }
}