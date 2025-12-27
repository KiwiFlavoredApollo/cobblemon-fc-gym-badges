package kiwiapollo.fcgymbadges.item;

import kiwiapollo.fcgymbadges.FCGymBadges;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class LockedGymBadgeItem {
    private static final List<Item> all = new ArrayList<>();

    public static final Item LOCKED_DARK_BADGE = register("locked_dark_badge", new LockedGymBadge(GymBadgeItem.DARK_BADGE));
    public static final Item LOCKED_LEAF_BADGE = register("locked_leaf_badge", new LockedGymBadge(GymBadgeItem.LEAF_BADGE));
    public static final Item LOCKED_FLYING_BADGE = register("locked_flying_badge", new LockedGymBadge(GymBadgeItem.FLYING_BADGE));
    public static final Item LOCKED_ROCK_BADGE = register("locked_rock_badge", new LockedGymBadge(GymBadgeItem.ROCK_BADGE));
    public static final Item LOCKED_ELECTRIC_BADGE = register("locked_electric_badge", new LockedGymBadge(GymBadgeItem.ELECTRIC_BADGE));
    public static final Item LOCKED_FIRE_BADGE = register("locked_fire_badge", new LockedGymBadge(GymBadgeItem.FIRE_BADGE));
    public static final Item LOCKED_ICE_BADGE = register("locked_ice_badge", new LockedGymBadge(GymBadgeItem.ICE_BADGE));
    public static final Item LOCKED_WATER_BADGE = register("locked_water_badge", new LockedGymBadge(GymBadgeItem.WATER_BADGE));
    public static final Item LOCKED_DRAGON_BADGE = register("locked_dragon_badge", new LockedGymBadge(GymBadgeItem.DRAGON_BADGE));
    public static final Item LOCKED_GROUND_BADGE = register("locked_ground_badge", new LockedGymBadge(GymBadgeItem.GROUND_BADGE));
    public static final Item LOCKED_PSYCHIC_BADGE = register("locked_psychic_badge", new LockedGymBadge(GymBadgeItem.PSYCHIC_BADGE));
    public static final Item LOCKED_GHOST_BADGE = register("locked_ghost_badge", new LockedGymBadge(GymBadgeItem.GHOST_BADGE));
    public static final Item LOCKED_WING_BADGE = register("locked_wing_badge", new LockedGymBadge(GymBadgeItem.WING_BADGE));
    public static final Item LOCKED_UNAWARE_BADGE = register("locked_unaware_badge", new LockedGymBadge(GymBadgeItem.UNAWARE_BADGE));
    public static final Item LOCKED_ICICLE_BADGE = register("locked_icicle_badge", new LockedGymBadge(GymBadgeItem.ICICLE_BADGE));
    public static final Item LOCKED_BUG_BADGE = register("locked_bug_badge", new LockedGymBadge(GymBadgeItem.BUG_BADGE));
    public static final Item LOCKED_FAIRY_BADGE = register("locked_fairy_badge", new LockedGymBadge(GymBadgeItem.FAIRY_BADGE));
    public static final Item LOCKED_POISON_BADGE = register("locked_poison_badge", new LockedGymBadge(GymBadgeItem.POISON_BADGE));

    public static void initialize(){

    }

    private static Item register(String name, Item item) {
        Identifier identifier = Identifier.of(FCGymBadges.MOD_ID, name);
        Item registered = Registry.register(Registries.ITEM, identifier, item);
        all.add(registered);
        return registered;
    }

    public static List<Item> getAll() {
        return new ArrayList<>(all);
    }
}
