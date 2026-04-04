package net.bayruby.multiblock_extra;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.level.BlockEvent.EntityPlaceEvent;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;

@Mod(MultiblockExtra.MODID)
public class MultiblockExtra {
    public static final String MODID = "multiblockextra";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MultiblockExtra(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this); // Register event handlers

        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, null);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Multiblock Extra initializing!");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {}

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Multiblock Extra server started!");
    }

    // The main event handler -- only triggers on "head" block placement
    @SubscribeEvent
    public void onBlockPlace(EntityPlaceEvent event) {
        if (event.getLevel() instanceof net.minecraft.world.level.Level level && !level.isClientSide) {
            Block block = event.getPlacedBlock().getBlock();
            if (block == Blocks.PUMPKIN || block == Blocks.ANVIL) {
                MultiblockSpawner.tryTrigger(level, event.getPos(), block);
            }
        }
    }
}