package kiwiapollo.fcgymbadges.item;

import net.minecraft.item.Item;

public class LockedGymBadge extends Item {
    private final GymBadgeItem badge;

    public LockedGymBadge(GymBadgeItem badge) {
        super(new Settings().maxCount(1));
        this.badge = badge;
    }

    public Item getGymBadge() {
        return badge.getItem();
    }
}