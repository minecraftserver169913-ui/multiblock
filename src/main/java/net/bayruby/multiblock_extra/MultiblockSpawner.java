package net.bayruby.multiblock_extra;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class MultiblockSpawner {

    public static void tryTrigger(Level level, BlockPos headPos, Block headBlock) {
        if (headBlock == Blocks.PUMPKIN) {
            // Umvuthi - T made of GOLD_BLOCK + PUMPKIN
            if (checkTStructure(level, headPos, Blocks.GOLD_BLOCK, Blocks.PUMPKIN)) {
                spawnMowziesMob(level, headPos, "mowziesmobs:umvuthi");
                removeTStructure(level, headPos);
            }
            // Frostmaw - T made of ICE + PUMPKIN
            else if (checkTStructure(level, headPos, Blocks.ICE, Blocks.PUMPKIN)) {
                spawnMowziesMob(level, headPos, "mowziesmobs:frostmaw");
                removeTStructure(level, headPos);
            }
        } else if (headBlock == Blocks.ANVIL) {
            // Ferrus Wroughtnaut - T of IRON_BLOCK + ANVIL
            if (checkTStructure(level, headPos, Blocks.IRON_BLOCK, Blocks.ANVIL)) {
                spawnMowziesMob(level, headPos, "mowziesmobs:ferrous_wroughtnaut");
                removeTStructure(level, headPos);
            }
        }
    }

    // Checks for T-shape centered at block BELOW given head position.
    private static boolean checkTStructure(Level level, BlockPos headPos, Block stemBlock, Block headBlock) {
        BlockPos center = headPos.below(); // center is block below the head
        // Check "up" for head
        if (!(level.getBlockState(headPos).getBlock() == headBlock)) return false;
        // Center and arms/leg
        if (!(level.getBlockState(center).getBlock() == stemBlock)) return false;
        if (!(level.getBlockState(center.north()).getBlock() == stemBlock)) return false;
        if (!(level.getBlockState(center.south()).getBlock() == stemBlock)) return false;
        if (!(level.getBlockState(center.east()).getBlock() == stemBlock)) return false;
        if (!(level.getBlockState(center.west()).getBlock() == stemBlock)) return false;
        if (!(level.getBlockState(center.below()).getBlock() == stemBlock)) return false;
        return true;
    }

    // Remove T-structure (and the head block)
    private static void removeTStructure(Level level, BlockPos headPos) {
        BlockPos center = headPos.below();
        level.destroyBlock(headPos, false); // Head
        level.destroyBlock(center, false); // Stem
        level.destroyBlock(center.north(), false);
        level.destroyBlock(center.south(), false);
        level.destroyBlock(center.east(), false);
        level.destroyBlock(center.west(), false);
        level.destroyBlock(center.below(), false);
    }

    // Summon the mob using command context; silent output
    public static void spawnMowziesMob(Level level, BlockPos pos, String mobId) {
        if (!level.isClientSide && level.getServer() != null) {
            String summonCmd = String.format("summon %s %f %f %f", mobId, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
            level.getServer().getCommands().performPrefixedCommand(
                    level.getServer().createCommandSourceStack().withSuppressedOutput(),
                    summonCmd
            );
        }
    }
}