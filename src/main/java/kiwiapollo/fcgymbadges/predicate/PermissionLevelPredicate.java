package kiwiapollo.fcgymbadges.predicate;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;

import java.util.function.Predicate;

public class PermissionLevelPredicate implements Predicate<PlayerEntity> {
    private final int level;

    public PermissionLevelPredicate(int level) {
        this.level = level;
    }

    @Override
    public boolean test(PlayerEntity player) {
        return player.hasPermissionLevel(level);
    }
}
