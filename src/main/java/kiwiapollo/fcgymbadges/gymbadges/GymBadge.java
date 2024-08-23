package kiwiapollo.fcgymbadges.gymbadges;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class GymBadge {
    public final Item item;
    public final Identifier identifier;

    public GymBadge(String path) {
        this.item = new Item(new FabricItemSettings().maxCount(1));
        this.identifier = Identifier.of(FractalCoffeeGymBadges.NAMESPACE, path);
    }
}