package kiwiapollo.fcgymbadges;

import com.mojang.logging.LogUtils;
import kiwiapollo.fcgymbadges.item.GymBadgeCrafterItem;
import kiwiapollo.fcgymbadges.item.GymBadgeItemGroup;
import kiwiapollo.fcgymbadges.item.GymBadgeItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.slf4j.Logger;

import java.util.Arrays;

public class FCGymBadges implements ModInitializer {
    public static final String MOD_ID = "fcgymbadges";
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        addItemGroup();

        addGymBadgeItems();
        addGymBadgeCrafterItems();
    }

    private void addItemGroup() {
        Registry.register(Registries.ITEM_GROUP, GymBadgeItemGroup.GYM_BADGE_ITEM_GROUP_KEY, GymBadgeItemGroup.GYM_BADGE_ITEM_GROUP);
    }

    private void addGymBadgeItems() {
        Arrays.stream(GymBadgeItem.values()).forEach(item -> {
            Registry.register(Registries.ITEM, item.getIdentifier(), item.getItem());
        });

        ItemGroupEvents.modifyEntriesEvent(GymBadgeItemGroup.GYM_BADGE_ITEM_GROUP_KEY).register(group -> {
            Arrays.stream(GymBadgeItem.values()).forEach(item -> {
                group.add(item.getItem());
            });
        });
    }

    private void addGymBadgeCrafterItems() {
        Arrays.stream(GymBadgeCrafterItem.values()).forEach(item -> {
            Registry.register(Registries.ITEM, item.getIdentifier(), item.getItem());
        });

        ItemGroupEvents.modifyEntriesEvent(GymBadgeItemGroup.GYM_BADGE_ITEM_GROUP_KEY).register(group -> {
            Arrays.stream(GymBadgeCrafterItem.values()).forEach(item -> {
                group.add(item.getItem());
            });
        });
    }
}
