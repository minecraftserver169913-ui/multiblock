package net.bayruby.multiblock_extra;

import net.minecraft.entity.monster.Monster;
import net.minecraft.entity.ai.goal.Goal;
import java.util.EnumSet;

public class UmvuthiEntity extends Monster {

    public UmvuthiEntity(EntityType<? extends Monster> type, World worldIn) {
        super(type, worldIn);
        this.setAI();
    }

    private void setAI() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal());
        this.goalSelector.addGoal(2, new WanderGoal());
    }

    // Custom melee attack goal
    class MeleeAttackGoal extends Goal {
        public MeleeAttackGoal() {
            this.setMutexFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean shouldExecute() {
            // Implement your logic to determine if the goal should execute
            return true;
        }

        @Override
        public void startExecuting() {
            // Implementation for start executing melee attack
        }

        @Override
        public void resetTask() {
            // Logic to reset task
        }
    }

    // Custom wander goal
    class WanderGoal extends Goal {
        public WanderGoal() {
            this.setMutexFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean shouldExecute() {
            // Implement logic for wandering
            return true;
        }

        @Override
        public void startExecuting() {
            // Logic to start wandering
        }

        @Override
        public void resetTask() {
            // Logic to stop wandering
        }
    }
}