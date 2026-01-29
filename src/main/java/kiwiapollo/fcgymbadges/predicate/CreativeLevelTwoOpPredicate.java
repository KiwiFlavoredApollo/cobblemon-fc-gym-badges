package kiwiapollo.fcgymbadges.predicate;

import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Predicate;

public class CreativeLevelTwoOpPredicate implements Predicate<PlayerEntity> {
    public CreativeLevelTwoOpPredicate() {

    }

    @Override
    public boolean test(PlayerEntity player) {
        return player.isCreativeLevelTwoOp();
    }
}
