package kiwiapollo.fcgymbadges.item;

import kiwiapollo.fcgymbadges.FCGymBadges;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class MiscItem {
    private static final List<Item> all = new ArrayList<>();

    public static final Item GYM_BADGE_UNLOCKER = register("gym_badge_unlocker", new GymBadgeUnlocker());

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
