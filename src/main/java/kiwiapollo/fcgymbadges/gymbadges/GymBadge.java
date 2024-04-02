package kiwiapollo.fcgymbadges.gymbadges;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public abstract class GymBadge {
    private final Item item;
    private final Identifier identifier;

    public GymBadge(String itemName) {
        this.item = new Item(new FabricItemSettings());
        this.identifier = new Identifier(FractalCoffeeGymBadges.NAMESPACE, itemName);

        registerItem();
    }

    private void registerItem() {
        Registry.register(Registries.ITEM, identifier, item);
    }

    public Item getItem() {
        return item;
    }
}
