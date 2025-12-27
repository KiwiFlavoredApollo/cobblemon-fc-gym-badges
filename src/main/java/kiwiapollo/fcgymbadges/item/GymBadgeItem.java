package kiwiapollo.fcgymbadges.item;

import kiwiapollo.fcgymbadges.FCGymBadges;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class GymBadgeItem {
    private static final List<Item> all = new ArrayList<>();

    public static final Item DARK_BADGE = register("dark_badge", new GymBadge());
    public static final Item LEAF_BADGE = register("leaf_badge", new GymBadge());
    public static final Item FLYING_BADGE = register("flying_badge", new GymBadge());
    public static final Item ROCK_BADGE = register("rock_badge", new GymBadge());
    public static final Item ELECTRIC_BADGE = register("electric_badge", new GymBadge());
    public static final Item FIRE_BADGE = register("fire_badge", new GymBadge());
    public static final Item ICE_BADGE = register("ice_badge", new GymBadge());
    public static final Item WATER_BADGE = register("water_badge", new GymBadge());
    public static final Item DRAGON_BADGE = register("dragon_badge", new GymBadge());
    public static final Item GROUND_BADGE = register("ground_badge", new GymBadge());
    public static final Item PSYCHIC_BADGE = register("psychic_badge", new GymBadge());
    public static final Item GHOST_BADGE = register("ghost_badge", new GymBadge());
    public static final Item WING_BADGE = register("wing_badge", new GymBadge());
    public static final Item UNAWARE_BADGE = register("unaware_badge", new GymBadge());
    public static final Item ICICLE_BADGE = register("icicle_badge", new GymBadge());
    public static final Item BUG_BADGE = register("bug_badge", new GymBadge());
    public static final Item FAIRY_BADGE = register("fairy_badge", new GymBadge());
    public static final Item POISON_BADGE = register("poison_badge", new GymBadge());

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
