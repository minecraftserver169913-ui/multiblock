package net.bayruby.multiblock_extra;

import net.bayruby.multiblock_extra.block.ModBlocks;
import net.bayruby.multiblock_extra.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.level.BlockEvent.EntityPlaceEvent;

@Mod(MultiblockExtra.MODID)
public class MultiblockExtra {
    public static final String MODID = "multiblockextra";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MultiblockExtra(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        NeoForge.EVENT_BUS.register(this);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.register(MultiblockExtraClient.class);
        }
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("[MultiblockExtra] Common setup initialized.");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        // No custom items yet
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS){
            event.accept(ModBlocks.ICEBOUND_SKULL.get());
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("[MultiblockExtra] Server starting.");
    }

    @SubscribeEvent
    public void onBlockPlace(EntityPlaceEvent event) {
        if (!(event.getLevel() instanceof Level level)) return;
        if (level.isClientSide) return;

        Block placed = event.getPlacedBlock().getBlock();
        if (placed != Blocks.PUMPKIN && placed != Blocks.ANVIL) {
            return;
        }

        LOGGER.debug("[MultiblockExtra] Head block placed: {} at {}", placed, event.getPos());
        MultiblockSpawner.tryTrigger(level, event.getPos(), placed);
    }
}
