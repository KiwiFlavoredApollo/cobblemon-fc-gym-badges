package kiwiapollo.fcgymbadges;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public abstract class GymBadge {

    private final Item item;
    private final Identifier identifier;
    protected String itemName;

    public GymBadge() {
        defineItemName();
        item = getInitItem();
        identifier = getInitIdentifier();
        registerItem();
    }

    private Item getInitItem() {
        return new Item(new FabricItemSettings());
    }

    private Identifier getInitIdentifier() {
        return new Identifier(FractalCoffeeGymBadges.NAMESPACE, itemName);
    }

    private void registerItem() {
        Registry.register(Registries.ITEM, identifier, item);
    }

    public Item getItem() {
        return item;
    }

    protected abstract void defineItemName();
}
