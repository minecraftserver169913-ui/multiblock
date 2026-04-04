package net.bayruby.multiblock_extra;

import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import java.util.List;

public class MultiblockSpawner {

    public static void detectAndSpawnEntities(Location origin, Player player) {
        // Check for a T-shaped structure
        if (isTShape(origin)) {
            // Spawn entities based on the detection
            spawnEntities(origin);
            player.sendMessage("A T-shaped multiblock has been detected and entities spawned!");
        } else {
            player.sendMessage("No valid T-shaped structure found.");
        }
    }

    private static boolean isTShape(Location origin) {
        // Assuming the structure is centered at the origin block
        Block base = origin.getBlock();
        Block[] structureBlocks = new Block[9];  // Placeholder for block checks

        // Check vertical blocks
        structureBlocks[0] = base.getRelative(0, 0, 0);  // Center
        structureBlocks[1] = base.getRelative(0, -1, 0); // Bottom
        structureBlocks[2] = base.getRelative(0, 1, 0);  // Top
        structureBlocks[3] = base.getRelative(-1, 0, 0); // Left
        structureBlocks[4] = base.getRelative(1, 0, 0);  // Right
        structureBlocks[5] = base.getRelative(0, 0, -1); // Back
        structureBlocks[6] = base.getRelative(0, 0, 1);  // Front

        // Check T shape conditions: Vertical column + horizontal row
        return (isMaterial(constantMaterial(), structureBlocks[1]) &&
                isMaterial(constantMaterial(), structureBlocks[2]) &&
                isMaterial(constantMaterial(), structureBlocks[3]) &&
                isMaterial(constantMaterial(), structureBlocks[4]) &&
                (isHeadType(structureBlocks[1]) || isHeadType(structureBlocks[2])));
    }

    private static boolean isMaterial(Material material, Block block) {
        return block.getType() == material;
    }

    private static boolean isHeadType(Block block) {
        return block.getType() == Material.PUMPKIN || block.getType() == Material.ANVIL;
    }

    private static void spawnEntities(Location location) {
        // Spawn entities here based on the type of T-shape detected
        // Example: Spawn a few entities
        location.getWorld().spawnEntity(location.add(1, 0, 0), EntityType.ZOMBIE);
        location.getWorld().spawnEntity(location.add(-1, 0, 0), EntityType.SKELETON);
        // Clear out the location for proper spawning
        location.add(-1, 0, 0);  // Reset to the origin
    }

    private static Material constantMaterial() {
        // Define the material type for the vertical blocks in T shape
        return Material.GOLD_BLOCK;  // Change based on requirements, can be gold, iron, or ice
    }
}