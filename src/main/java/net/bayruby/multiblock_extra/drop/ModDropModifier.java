package net.bayruby.multiblock_extra.drop;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class ModDropModifier extends LootModifier {

    private final ItemStack drop;

    public ModDropModifier(LootItemCondition[] conditions, ItemStack drop) {
        super(conditions);
        this.drop = drop;
    }

    public ItemStack getDrop() {
        return drop;
    }

    public static final MapCodec<ModDropModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            LootModifier.codecStart(instance)
                    .and(ItemStack.CODEC.fieldOf("drop").forGetter(ModDropModifier::getDrop))
                    .apply(instance, ModDropModifier::new)
    );

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);

        if (entity != null) {
            generatedLoot.add(drop.copy());
        }

        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
