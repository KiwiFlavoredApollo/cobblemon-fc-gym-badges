package kiwiapollo.fcgymbadges.commands.predicates;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.types.PermissionNode;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.function.Predicate;

public class ClearLeafBadgeCommandPredicate extends GymBadgeCommandPredicate {
    public ClearLeafBadgeCommandPredicate() {
        super(String.format("%s.%s.%s", FractalCoffeeGymBadges.NAMESPACE, "leafbadge", "clear"));
    }
}
