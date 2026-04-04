package net.bayruby.multiblock_extra;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;

public class MultiblockSpawner {

    public static void checkForMultiblock(Level level, BlockPos pos) {
        if (level.isClientSide) return;

        if (checkUmvuthiStructure(level, pos)) {
            spawnUmvuthi(level, pos);
            return;
        }

        if (checkFerrusStructure(level, pos)) {
            spawnFerrus(level, pos);
            return;
        }

        if (checkFrostmawStructure(level, pos)) {
            spawnFrostmaw(level, pos);
            return;
        }
    }

    private static boolean checkUmvuthiStructure(Level level, BlockPos pos) {
        return checkTStructure(level, pos, Blocks.GOLD_BLOCK, Blocks.PUMPKIN);
    }

    private static boolean checkFerrusStructure(Level level, BlockPos pos) {
        return checkTStructure(level, pos, Blocks.IRON_BLOCK, Blocks.ANVIL);
    }

    private static boolean checkFrostmawStructure(Level level, BlockPos pos) {
        return checkTStructure(level, pos, Blocks.ICE, Blocks.PUMPKIN);
    }

    private static boolean checkTStructure(Level level, BlockPos pos, Block stemBlock, Block headBlock) {
        try {
            BlockPos center = pos;
            BlockPos up1 = center.above();
            BlockPos up2 = center.above(2);
            BlockPos down1 = center.below();
            BlockPos left = center.west();
            BlockPos right = center.east();
            BlockPos forward = center.north();
            BlockPos back = center.south();

            if (!isBlock(level, up2, headBlock)) return false;
            if (!isBlock(level, up1, stemBlock)) return false;
            if (!isBlock(level, center, stemBlock)) return false;
            if (!isBlock(level, left, stemBlock)) return false;
            if (!isBlock(level, right, stemBlock)) return false;
            if (!isBlock(level, forward, stemBlock)) return false;
            if (!isBlock(level, back, stemBlock)) return false;
            if (!isBlock(level, down1, stemBlock)) return false;

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isBlock(Level level, BlockPos pos, Block block) {
        return level.getBlockState(pos).getBlock() == block;
    }

    private static void spawnUmvuthi(Level level, BlockPos pos) {
        UmvuthiEntity entity = new UmvuthiEntity(MultiblockExtra.UMVUTHI.get(), level);
        entity.moveTo(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 0, 0);
        level.addFreshEntity(entity);
        removeStructure(level, pos);
        MultiblockExtra.LOGGER.info("Umvuthi spawned at {}", pos);
    }

    private static void spawnFerrus(Level level, BlockPos pos) {
        FerrusWraughtnaught entity = new FerrusWraughtnaught(MultiblockExtra.FERRUS_WRAUGHTNAUGHT.get(), level);
        entity.moveTo(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 0, 0);
        level.addFreshEntity(entity);
        removeStructure(level, pos);
        MultiblockExtra.LOGGER.info("Ferrus Wraughtnaught spawned at {}", pos);
    }

    private static void spawnFrostmaw(Level level, BlockPos pos) {
        FrostmawEntity entity = new FrostmawEntity(MultiblockExtra.FROSTMAW.get(), level);
        entity.moveTo(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 0, 0);
        level.addFreshEntity(entity);
        removeStructure(level, pos);
        MultiblockExtra.LOGGER.info("Frostmaw spawned at {}", pos);
    }

    private static void removeStructure(Level level, BlockPos pos) {
        level.destroyBlock(pos.above(2), false);
        level.destroyBlock(pos.above(), false);
        level.destroyBlock(pos, false);
        level.destroyBlock(pos.below(), false);
        level.destroyBlock(pos.west(), false);
        level.destroyBlock(pos.east(), false);
        level.destroyBlock(pos.north(), false);
        level.destroyBlock(pos.south(), false);
    }
}