package kiwiapollo.fcgymbadges.item;

import kiwiapollo.fcgymbadges.FCGymBadges;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public enum GymBadgeCrafterItem {
    DARK_BADGE_CRAFTER("dark_badge_crafter", new GymBadgeCrafter(GymBadgeItem.DARK_BADGE)),
    LEAF_BADGE_CRAFTER("leaf_badge_crafter", new GymBadgeCrafter(GymBadgeItem.LEAF_BADGE)),
    FLYING_BADGE_CRAFTER("flying_badge_crafter", new GymBadgeCrafter(GymBadgeItem.FLYING_BADGE)),
    ROCK_BADGE_CRAFTER("rock_badge_crafter", new GymBadgeCrafter(GymBadgeItem.ROCK_BADGE)),
    ELECTRIC_BADGE_CRAFTER("electric_badge_crafter", new GymBadgeCrafter(GymBadgeItem.ELECTRIC_BADGE)),
    FIRE_BADGE_CRAFTER("fire_badge_crafter", new GymBadgeCrafter(GymBadgeItem.FIRE_BADGE)),
    ICE_BADGE_CRAFTER("ice_badge_crafter", new GymBadgeCrafter(GymBadgeItem.ICE_BADGE)),
    WATER_BADGE_CRAFTER("water_badge_crafter", new GymBadgeCrafter(GymBadgeItem.WATER_BADGE)),
    DRAGON_BADGE_CRAFTER("dragon_badge_crafter", new GymBadgeCrafter(GymBadgeItem.DRAGON_BADGE)),
    GROUND_BADGE_CRAFTER("ground_badge_crafter", new GymBadgeCrafter(GymBadgeItem.GROUND_BADGE)),
    PSYCHIC_BADGE_CRAFTER("psychic_badge_crafter", new GymBadgeCrafter(GymBadgeItem.PSYCHIC_BADGE)),
    GHOST_BADGE_CRAFTER("ghost_badge_crafter", new GymBadgeCrafter(GymBadgeItem.GHOST_BADGE)),
    WING_BADGE_CRAFTER("wing_badge_crafter", new GymBadgeCrafter(GymBadgeItem.WING_BADGE)),
    UNAWARE_BADGE_CRAFTER("unaware_badge_crafter", new GymBadgeCrafter(GymBadgeItem.UNAWARE_BADGE)),
    ICICLE_BADGE_CRAFTER("icicle_badge_crafter", new GymBadgeCrafter(GymBadgeItem.ICICLE_BADGE)),
    BUG_BADGE_CRAFTER("bug_badge_crafter", new GymBadgeCrafter(GymBadgeItem.BUG_BADGE)),
    FAIRY_BADGE_CRAFTER("fairy_badge_crafter", new GymBadgeCrafter(GymBadgeItem.FAIRY_BADGE));

    private final Identifier identifier;
    private final Item item;

    GymBadgeCrafterItem(String name, Item item) {
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
