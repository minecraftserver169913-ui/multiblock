package net.bayruby.multiblock_extra;

import net.bayruby.multiblock_extra.block.ModSkullTypes;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class MultiblockExtraClient {

    @SubscribeEvent
    public static void onCreateSkullModels(EntityRenderersEvent.CreateSkullModels event) {
        SkullBlockRenderer.SKIN_BY_TYPE.put(
                ModSkullTypes.ICEBOUND,
                ResourceLocation.fromNamespaceAndPath(
                        MultiblockExtra.MODID,
                        "textures/entity/icebound_skull.png"
                )
        );
    }
}
