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
        itemGroupIcon = getInitItemGroupIcon();
        itemGroup = getInitItemGroup();
        identifier = getInitIdentifier();
        registerItemGroup();
    }

    private Item getInitItemGroupIcon() {
        return FractalCoffeeGymBadges.DARK_TYPE_GYM_BADGE.getItem();
    }

    private ItemGroup getInitItemGroup() {
        return FabricItemGroup.builder()
                .icon(() -> new ItemStack(itemGroupIcon))
                .displayName(Text.translatable("Cobblemon FC Gym Badges"))
                .build();
    }

    private Identifier getInitIdentifier() {
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
