package kiwiapollo.fcgymbadges.gymbadges;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
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

        registerItemGroup();
    }

    private Item createItemGroupIcon() {
        return FractalCoffeeGymBadges.DARK_BADGE.getItem();
    }

    private ItemGroup createItemGroup() {
        return FabricItemGroup.builder()
                .icon(() -> new ItemStack(itemGroupIcon))
                .displayName(Text.translatable("Cobblemon FC Gym Badges"))
                .build();
    }

    private Identifier createIdentifier() {
        return new Identifier(FractalCoffeeGymBadges.NAMESPACE, "item_group");
    }

    private void registerItemGroup() {
        Registry.register(Registries.ITEM_GROUP, identifier, itemGroup);
    }

    public void addGymBadge(GymBadge gymBadge) {
        var groupRegistryKey = RegistryKey.of(Registries.ITEM_GROUP.getKey(), identifier);
        ItemGroupEvents.modifyEntriesEvent(groupRegistryKey).register(itemGroup -> {
            itemGroup.add(gymBadge.getItem());
        });
    }
}
