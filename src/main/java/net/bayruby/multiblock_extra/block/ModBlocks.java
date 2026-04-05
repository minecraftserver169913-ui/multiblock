package net.bayruby.multiblock_extra.block;

import net.bayruby.multiblock_extra.MultiblockExtra;
import net.bayruby.multiblock_extra.item.ModItems;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MultiblockExtra.MODID);


    public static final DeferredBlock<Block> ICEBOUND = registerBlock("icebound",
            () -> new Block(BlockBehaviour.Properties.of()
                    .sound(SoundType.BONE_BLOCK)
                    .strength(1f)
                    .noOcclusion()) {
                private static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 8, 12);

                @Override
                public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    return SHAPE;
                }

                @Override
                public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    return SHAPE;
                }
            });

    public static final DeferredBlock<Block> UMVUTHI_SKULL = registerBlock("umvuthi_skull",
            () -> new Block(BlockBehaviour.Properties.of()
                    .sound(SoundType.BONE_BLOCK)
                    .strength(1f)
                    .noOcclusion()) {
                private static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 8, 12);


                @Override
                public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    return SHAPE;
                }

                @Override
                public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    return SHAPE;
                }

            });

    public static final DeferredBlock<Block> RUSTED_HELM = registerBlock("rusted_helm",
            () -> new Block(BlockBehaviour.Properties.of()
                    .sound(SoundType.BONE_BLOCK)
                    .strength(1f)
                    .noOcclusion()) {
                private static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 8, 12);


                @Override
                public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    return SHAPE;
                }

                @Override
                public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    return SHAPE;
                }

            });



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}