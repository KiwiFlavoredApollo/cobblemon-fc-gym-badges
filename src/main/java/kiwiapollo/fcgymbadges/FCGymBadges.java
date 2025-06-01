package kiwiapollo.fcgymbadges;

import com.mojang.logging.LogUtils;
import kiwiapollo.fcgymbadges.item.LockedGymBadgeItem;
import kiwiapollo.fcgymbadges.item.GymBadgeItemGroup;
import kiwiapollo.fcgymbadges.item.GymBadgeItem;
import kiwiapollo.fcgymbadges.item.MiscItem;
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
        addLockedGymBadgeItems();
        addMiscItems();
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

    private void addLockedGymBadgeItems() {
        Arrays.stream(LockedGymBadgeItem.values()).forEach(item -> {
            Registry.register(Registries.ITEM, item.getIdentifier(), item.getItem());
        });

        ItemGroupEvents.modifyEntriesEvent(GymBadgeItemGroup.GYM_BADGE_ITEM_GROUP_KEY).register(group -> {
            Arrays.stream(LockedGymBadgeItem.values()).forEach(item -> {
                group.add(item.getItem());
            });
        });
    }

    private void addMiscItems() {
        Arrays.stream(MiscItem.values()).forEach(item -> {
            Registry.register(Registries.ITEM, item.getIdentifier(), item.getItem());
        });

        ItemGroupEvents.modifyEntriesEvent(GymBadgeItemGroup.GYM_BADGE_ITEM_GROUP_KEY).register(group -> {
            Arrays.stream(MiscItem.values()).forEach(item -> {
                group.add(item.getItem());
            });
        });
    }
}
