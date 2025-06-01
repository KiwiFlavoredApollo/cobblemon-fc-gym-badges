package kiwiapollo.fcgymbadges.predicate;

import net.fabricmc.loader.api.FabricLoader;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedPermissionData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.util.Tristate;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;
import java.util.function.Predicate;

public class LuckPermsPredicate implements Predicate<PlayerEntity> {
    private final List<String> permissions;

    public LuckPermsPredicate(List<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean test(PlayerEntity player) {
        if (!FabricLoader.getInstance().isModLoaded("luckperms")) {
            return false;
        }

        User user = LuckPermsProvider.get().getUserManager().getUser(player.getUuid());
        CachedPermissionData userCachedPermissionData = user.getCachedData().getPermissionData();

        return permissions.stream().map(userCachedPermissionData::checkPermission).anyMatch(Tristate::asBoolean);
    }
}
