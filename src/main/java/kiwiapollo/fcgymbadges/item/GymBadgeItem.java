package kiwiapollo.fcgymbadges.item;

import kiwiapollo.fcgymbadges.FCGymBadges;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public enum GymBadgeItem {
    DARK_BADGE("dark_badge", new GymBadge()),
    LEAF_BADGE("leaf_badge", new GymBadge()),
    FLYING_BADGE("flying_badge", new GymBadge()),
    ROCK_BADGE("rock_badge", new GymBadge()),
    ELECTRIC_BADGE("electric_badge", new GymBadge()),
    FIRE_BADGE("fire_badge", new GymBadge()),
    ICE_BADGE("ice_badge", new GymBadge()),
    WATER_BADGE("water_badge", new GymBadge()),
    DRAGON_BADGE("dragon_badge", new GymBadge()),
    GROUND_BADGE("ground_badge", new GymBadge()),
    PSYCHIC_BADGE("psychic_badge", new GymBadge()),
    GHOST_BADGE("ghost_badge", new GymBadge()),
    WING_BADGE("wing_badge", new GymBadge()),
    UNAWARE_BADGE("unaware_badge", new GymBadge()),
    ICICLE_BADGE("icicle_badge", new GymBadge()),
    BUG_BADGE("bug_badge", new GymBadge()),
    FAIRY_BADGE("fairy_badge", new GymBadge());

    private final Identifier identifier;
    private final Item item;

    GymBadgeItem(String name, Item item) {
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
