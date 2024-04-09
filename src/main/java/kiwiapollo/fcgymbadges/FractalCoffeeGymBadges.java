package kiwiapollo.fcgymbadges;

import com.mojang.logging.LogUtils;
import kiwiapollo.fcgymbadges.commands.GymBadgeCommands;
import kiwiapollo.fcgymbadges.gymbadges.*;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;

public class FractalCoffeeGymBadges implements ModInitializer {
    public static final String NAMESPACE = "fcgymbadges";
    public static final GymBadge DARK_BADGE = new DarkBadge();
    public static final GymBadge LEAF_BADGE = new LeafBadge();
    public static final GymBadge FLYING_BADGE = new FlyingBadge();
    public static final GymBadge ROCK_BADGE = new RockBadge();
    public static final GymBadge ELECTRIC_BADGE = new ElectricBadge();
    public static final GymBadge FIRE_BADGE = new FireBadge();
    public static final GymBadgeItemGroup GYM_BADGE_ITEM_GROUP = new GymBadgeItemGroup();
    public static final GymBadgeCommands GYM_BADGE_COMMANDS = new GymBadgeCommands();
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        registerGymBadges();
        registerGymBadgeItemGroup();
        registerCommands();
    }

    private void registerGymBadges() {
        DARK_BADGE.register();
        LEAF_BADGE.register();
        FLYING_BADGE.register();
        ROCK_BADGE.register();
        ELECTRIC_BADGE.register();
        FIRE_BADGE.register();
    }

    private void registerGymBadgeItemGroup() {
        GYM_BADGE_ITEM_GROUP.register();
    }

    private void registerCommands() {
        GYM_BADGE_COMMANDS.register();
    }
}
