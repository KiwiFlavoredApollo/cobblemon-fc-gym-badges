package kiwiapollo.fcgymbadges;

import kiwiapollo.fcgymbadges.item.*;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FCGymBadges implements ModInitializer {
    public static final String MOD_ID = "fcgymbadges";
    public static final Logger LOGGER = LoggerFactory.getLogger("FCGymBadges");

    @Override
    public void onInitialize() {
        CustomItem.initialize();

        CustomItemGroup.initialize();
    }
}
