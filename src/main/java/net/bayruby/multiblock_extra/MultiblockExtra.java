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
import net.neoforged.neoforge.event.level.BlockEvent;

@Mod(MultiblockExtra.MODID)
public class MultiblockExtra {
    public static final String MODID = "multiblockextra";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<UmvuthiEntity>> UMVUTHI = ENTITY_TYPES.register("umvuthi",
            () -> EntityType.Builder.of(UmvuthiEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f)
                    .clientTrackingRange(8)
                    .build("umvuthi"));

    public static final DeferredHolder<EntityType<?>, EntityType<FerrusWraughtnaught>> FERRUS_WRAUGHTNAUGHT = ENTITY_TYPES.register("ferrus_wraughtnaught",
            () -> EntityType.Builder.of(FerrusWraughtnaught::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f)
                    .clientTrackingRange(8)
                    .build("ferrus_wraughtnaught"));

    public static final DeferredHolder<EntityType<?>, EntityType<FrostmawEntity>> FROSTMAW = ENTITY_TYPES.register("frostmaw",
            () -> EntityType.Builder.of(FrostmawEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 1.8f)
                    .clientTrackingRange(8)
                    .build("frostmaw"));

    public MultiblockExtra(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        ENTITY_TYPES.register(modEventBus);
        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Multiblock Extra initializing!");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Multiblock Extra server started!");
    }

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.Place event) {
        if (!event.getLevel().isClientSide) {
            MultiblockSpawner.checkForMultiblock(event.getLevel(), event.getPos());
        }
    }
}