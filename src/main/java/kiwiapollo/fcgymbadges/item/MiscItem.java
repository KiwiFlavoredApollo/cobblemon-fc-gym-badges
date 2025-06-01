package kiwiapollo.fcgymbadges.item;

import kiwiapollo.fcgymbadges.FCGymBadges;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public enum MiscItem {
    GYM_BADGE_UNLOCKER("gym_badge_unlocker", new GymBadgeUnlocker());

    private final Identifier identifier;
    private final Item item;

    MiscItem(String name, Item item) {
        this.identifier = Identifier.of(FCGymBadges.MOD_ID, name);
        this.item = item;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Item getItem() {
        return item;
    }
}
