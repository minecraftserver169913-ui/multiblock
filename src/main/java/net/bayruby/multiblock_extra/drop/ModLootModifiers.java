package net.bayruby.multiblock_extra.drop;

import com.mojang.serialization.MapCodec;
import net.bayruby.multiblock_extra.MultiblockExtra;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModLootModifiers {

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MultiblockExtra.MODID);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<ModDropModifier>> MOB_DROP =
            LOOT_MODIFIERS.register("mob_drop", () -> ModDropModifier.CODEC);
}
