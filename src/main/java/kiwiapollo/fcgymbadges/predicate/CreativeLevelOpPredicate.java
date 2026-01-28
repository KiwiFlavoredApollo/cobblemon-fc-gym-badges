package kiwiapollo.fcgymbadges.predicate;

import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Predicate;

public class CreativeLevelOpPredicate implements Predicate<PlayerEntity> {
    public CreativeLevelOpPredicate() {

    }

    @Override
    public boolean test(PlayerEntity player) {
        return player.isCreativeLevelTwoOp();
    }
}
