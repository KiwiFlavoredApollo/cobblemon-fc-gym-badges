package kiwiapollo.fcgymbadges.gymbadges;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class GymBadgeItemGroup {
    private final Item itemGroupIcon;
    private final ItemGroup itemGroup;
    private final Identifier identifier;

    public GymBadgeItemGroup() {
        itemGroupIcon = createItemGroupIcon();
        itemGroup = createItemGroup();
        identifier = createIdentifier();
    }

    private Item createItemGroupIcon() {
        return FractalCoffeeGymBadges.DARK_BADGE.getItem();
    }

    private ItemGroup createItemGroup() {
        return FabricItemGroup.builder()
                .icon(() -> new ItemStack(itemGroupIcon))
                .entries((context, entries) -> {
                    entries.add(FractalCoffeeGymBadges.DARK_BADGE.getItem());
                    entries.add(FractalCoffeeGymBadges.LEAF_BADGE.getItem());
                    entries.add(FractalCoffeeGymBadges.FLYING_BADGE.getItem());
                    entries.add(FractalCoffeeGymBadges.ROCK_BADGE.getItem());
                    entries.add(FractalCoffeeGymBadges.ELECTRIC_BADGE.getItem());
                    entries.add(FractalCoffeeGymBadges.FIRE_BADGE.getItem());
                })
                .displayName(Text.translatable("Cobblemon FC Gym Badges"))
                .build();
    }

    private Identifier createIdentifier() {
        return new Identifier(FractalCoffeeGymBadges.NAMESPACE, "item_group");
    }

    public void register() {
        Registry.register(Registries.ITEM_GROUP, identifier, itemGroup);
    }
}
