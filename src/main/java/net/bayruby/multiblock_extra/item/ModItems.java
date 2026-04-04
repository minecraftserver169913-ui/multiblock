package net.bayruby.multiblock_extra.item;

import net.bayruby.multiblock_extra.MultiblockExtra;
import net.bayruby.multiblock_extra.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MultiblockExtra.MODID);

    /*
    public static final DeferredItem<BlockItem> ICEBOUND_SKULL_ITEM =
            ITEMS.register("icebound_skull",
                    () -> new BlockItem(ModBlocks.ICEBOUND_SKULL.get(), new Item.Properties()));
*/


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
