package kiwiapollo.fcgymbadges.item;

import kiwiapollo.fcgymbadges.FCGymBadges;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public enum LockedGymBadgeItem {
    LOCKED_DARK_BADGE("locked_dark_badge", new LockedGymBadge(GymBadgeItem.DARK_BADGE)),
    LOCKED_LEAF_BADGE("locked_leaf_badge", new LockedGymBadge(GymBadgeItem.LEAF_BADGE)),
    LOCKED_FLYING_BADGE("locked_flying_badge", new LockedGymBadge(GymBadgeItem.FLYING_BADGE)),
    LOCKED_ROCK_BADGE("locked_rock_badge", new LockedGymBadge(GymBadgeItem.ROCK_BADGE)),
    LOCKED_ELECTRIC_BADGE("locked_electric_badge", new LockedGymBadge(GymBadgeItem.ELECTRIC_BADGE)),
    LOCKED_FIRE_BADGE("locked_fire_badge", new LockedGymBadge(GymBadgeItem.FIRE_BADGE)),
    LOCKED_ICE_BADGE("locked_ice_badge", new LockedGymBadge(GymBadgeItem.ICE_BADGE)),
    LOCKED_WATER_BADGE("locked_water_badge", new LockedGymBadge(GymBadgeItem.WATER_BADGE)),
    LOCKED_DRAGON_BADGE("locked_dragon_badge", new LockedGymBadge(GymBadgeItem.DRAGON_BADGE)),
    LOCKED_GROUND_BADGE("locked_ground_badge", new LockedGymBadge(GymBadgeItem.GROUND_BADGE)),
    LOCKED_PSYCHIC_BADGE("locked_psychic_badge", new LockedGymBadge(GymBadgeItem.PSYCHIC_BADGE)),
    LOCKED_GHOST_BADGE("locked_ghost_badge", new LockedGymBadge(GymBadgeItem.GHOST_BADGE)),
    LOCKED_WING_BADGE("locked_wing_badge", new LockedGymBadge(GymBadgeItem.WING_BADGE)),
    LOCKED_UNAWARE_BADGE("locked_unaware_badge", new LockedGymBadge(GymBadgeItem.UNAWARE_BADGE)),
    LOCKED_ICICLE_BADGE("locked_icicle_badge", new LockedGymBadge(GymBadgeItem.ICICLE_BADGE)),
    LOCKED_BUG_BADGE("locked_bug_badge", new LockedGymBadge(GymBadgeItem.BUG_BADGE)),
    LOCKED_FAIRY_BADGE("locked_fairy_badge", new LockedGymBadge(GymBadgeItem.FAIRY_BADGE));

    private final Identifier identifier;
    private final Item item;

    LockedGymBadgeItem(String name, Item item) {
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
