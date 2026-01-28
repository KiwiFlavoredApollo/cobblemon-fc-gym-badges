package kiwiapollo.fcgymbadges.item;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class CustomTagRegistry {
    public static final TagKey<Item> BADGEBOX = TagKey.of(RegistryKeys.ITEM, Identifier.of("badgebox", "badges"));
}
