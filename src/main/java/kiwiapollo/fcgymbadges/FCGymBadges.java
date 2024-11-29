package kiwiapollo.fcgymbadges;

import com.mojang.logging.LogUtils;
import kiwiapollo.fcgymbadges.command.FCGymBadgesCommand;
import kiwiapollo.fcgymbadges.economy.Economy;
import kiwiapollo.fcgymbadges.item.GymBadgeItemGroup;
import kiwiapollo.fcgymbadges.item.GymBadges;
import kiwiapollo.fcgymbadges.config.Config;
import kiwiapollo.fcgymbadges.config.ConfigLoader;
import kiwiapollo.fcgymbadges.economy.EconomyFactory;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.slf4j.Logger;

import java.util.Arrays;

public class FCGymBadges implements ModInitializer {
    public static final String MOD_ID = "fcgymbadges";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static Config config = ConfigLoader.load();
    public static Economy economy = EconomyFactory.create(config.economy);

    @Override
    public void onInitialize() {
        Arrays.stream(GymBadges.values()).forEach(badge -> {
            Registry.register(Registries.ITEM, badge.getIdentifier(), badge.getItem());
        });

        Registry.register(Registries.ITEM_GROUP, GymBadgeItemGroup.GYM_BADGE_ITEM_GROUP_KEY, GymBadgeItemGroup.GYM_BADGE_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(GymBadgeItemGroup.GYM_BADGE_ITEM_GROUP_KEY).register(itemGroup -> {
            Arrays.stream(GymBadges.values()).forEach(badge -> {
                itemGroup.add(badge.getItem());
            });
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(new FCGymBadgesCommand());
        });
    }
}
