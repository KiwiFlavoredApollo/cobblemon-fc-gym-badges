package kiwiapollo.fcgymbadges.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

public class LockedGymBadge extends Item {
    private final GymBadgeItem badge;

    public LockedGymBadge(GymBadgeItem badge) {
        super(new Settings().maxCount(1));
        this.badge = badge;
    }

    public Item getGymBadge() {
        return badge.getItem();
    }

    public String getPermissionNode() {
        return Registries.ITEM.getId(getGymBadge()).getPath().replace("_", "");
    }
}