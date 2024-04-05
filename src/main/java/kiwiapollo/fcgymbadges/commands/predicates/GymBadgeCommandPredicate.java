package kiwiapollo.fcgymbadges.commands.predicates;

import kiwiapollo.fcgymbadges.exceptions.LuckPermsNotLoadedException;
import kiwiapollo.fcgymbadges.exceptions.PlayerNotExistException;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.types.PermissionNode;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.function.Predicate;

public abstract class GymBadgeCommandPredicate implements Predicate<ServerCommandSource> {
    private static final int OP_LEVEL = 2;
    private final String permission;

    public GymBadgeCommandPredicate(String permission) {
        this.permission = permission;
    }

    @Override
    public boolean test(ServerCommandSource source) {
        try {
            assertExistPlayer(source);
            assertExistLuckPerms();
            return isExistLuckPermPermission(source);
        } catch (PlayerNotExistException e) {
            return true;
        } catch (LuckPermsNotLoadedException e) {
            return isExistOpPermission(source);
        }
    }

    private void assertExistPlayer(ServerCommandSource source) throws PlayerNotExistException {
        if (source.getPlayer() == null) {
            throw new PlayerNotExistException();
        }
    }

    private void assertExistLuckPerms() throws LuckPermsNotLoadedException {
        try {
            LuckPermsProvider.get();
        } catch (IllegalStateException e) {
            throw new LuckPermsNotLoadedException();
        }
    }

    protected boolean isExistLuckPermPermission(ServerCommandSource source) {
        ServerPlayerEntity player = source.getPlayer();
        PermissionNode permissionNode = createPermissionNode();
        return LuckPermsProvider.get().getPlayerAdapter(ServerPlayerEntity.class)
                .getPermissionData(player).checkPermission(permissionNode.getPermission()).asBoolean();
    }

    private PermissionNode createPermissionNode() {
        return PermissionNode.builder(permission).value(false).build();
    }

    private boolean isExistOpPermission(ServerCommandSource source) {
        ServerPlayerEntity player = source.getPlayer();
        return player.hasPermissionLevel(OP_LEVEL);
    }
}
