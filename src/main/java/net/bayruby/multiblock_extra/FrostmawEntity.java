package net.bayruby.multiblock_extra;

import net.minecraft.entity.monster.Monster;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class FrostmawEntity extends Monster {
    private static final int MAX_HEALTH = 60;
    private static final double MOVEMENT_SPEED = 0.3;
    private static final int ATTACK_DAMAGE = 10;
    private static final int FOLLOW_RANGE = 32;

    public FrostmawEntity(EntityType<? extends FrostmawEntity> type, Level level) {
        super(type, level);
        this.setHealth(MAX_HEALTH);
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(MOVEMENT_SPEED);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(ATTACK_DAMAGE);
        this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(FOLLOW_RANGE);
    }

    // Additional FrostmawEntity-specific methods can go here
}