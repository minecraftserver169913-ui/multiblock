package net.bayruby.multiblock_extra.block;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.SkullBlock;

public enum ModSkullTypes implements SkullBlock.Type, StringRepresentable {

    ICEBOUND("icebound"),
    UMVUTHI("umvuthi"),
    RUSTED_KNIGHT("rusted_knight");

    private final String name;

    ModSkullTypes(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
