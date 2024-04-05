package kiwiapollo.fcgymbadges.commands.predicates;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.types.PermissionNode;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class ClearFlyingBadgeCommandPredicate extends GymBadgeCommandPredicate {
    public ClearFlyingBadgeCommandPredicate() {
        super(String.format("%s.%s.%s", FractalCoffeeGymBadges.NAMESPACE, "flyingbadge", "clear"));
    }
}
