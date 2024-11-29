package kiwiapollo.fcgymbadges.item;

import kiwiapollo.fcgymbadges.FCGymBadges;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class GymBadgeItemGroup {
    public static final RegistryKey<ItemGroup> GYM_BADGE_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(FCGymBadges.MOD_ID, "item_group"));
    public static final ItemGroup GYM_BADGE_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Registries.ITEM.get(Identifier.of(FCGymBadges.MOD_ID, "fire_badge"))))
            .displayName(Text.translatable("Cobblemon FC Gym Badges"))
            .build();
}
