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


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}