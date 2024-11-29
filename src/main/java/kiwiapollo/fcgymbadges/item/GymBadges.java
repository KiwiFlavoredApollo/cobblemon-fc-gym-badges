package kiwiapollo.fcgymbadges.item;

import kiwiapollo.fcgymbadges.FCGymBadges;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public enum GymBadges {
    DARK_BADGE(Identifier.of(FCGymBadges.MOD_ID, "dark_badge"), new GymBadgeItem()),
    LEAF_BADGE(Identifier.of(FCGymBadges.MOD_ID, "leaf_badge"), new GymBadgeItem()),
    FLYING_BADGE(Identifier.of(FCGymBadges.MOD_ID, "flying_badge"), new GymBadgeItem()),
    ROCK_BADGE(Identifier.of(FCGymBadges.MOD_ID, "rock_badge"), new GymBadgeItem()),
    ELECTRIC_BADGE(Identifier.of(FCGymBadges.MOD_ID, "electric_badge"), new GymBadgeItem()),
    FIRE_BADGE(Identifier.of(FCGymBadges.MOD_ID, "fire_badge"), new GymBadgeItem()),
    ICE_BADGE(Identifier.of(FCGymBadges.MOD_ID, "ice_badge"), new GymBadgeItem()),
    WATER_BADGE(Identifier.of(FCGymBadges.MOD_ID, "water_badge"), new GymBadgeItem()),
    DRAGON_BADGE(Identifier.of(FCGymBadges.MOD_ID, "dragon_badge"), new GymBadgeItem()),
    GROUND_BADGE(Identifier.of(FCGymBadges.MOD_ID, "ground_badge"), new GymBadgeItem()),
    PSYCHIC_BADGE(Identifier.of(FCGymBadges.MOD_ID, "psychic_badge"), new GymBadgeItem()),
    GHOST_BADGE(Identifier.of(FCGymBadges.MOD_ID, "ghost_badge"), new GymBadgeItem()),
    WING_BADGE(Identifier.of(FCGymBadges.MOD_ID, "wing_badge"), new GymBadgeItem()),
    UNAWARE_BADGE(Identifier.of(FCGymBadges.MOD_ID, "unaware_badge"), new GymBadgeItem()),
    ICICLE_BADGE(Identifier.of(FCGymBadges.MOD_ID, "icicle_badge"), new GymBadgeItem()),
    BUG_BADGE(Identifier.of(FCGymBadges.MOD_ID, "bug_badge"), new GymBadgeItem()),
    FAIRY_BADGE(Identifier.of(FCGymBadges.MOD_ID, "fairy_badge"), new GymBadgeItem());

    private final Identifier identifier;
    private final Item item;

    GymBadges(Identifier identifier, Item item) {
        this.identifier = identifier;
        this.item = item;
    }

    public Identifier getIdentifier() {
        return identifier;
    }
    
    public Item getItem() {
        return item;
    }
}
