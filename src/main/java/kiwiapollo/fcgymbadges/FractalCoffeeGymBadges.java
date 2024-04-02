package kiwiapollo.fcgymbadges;

import com.mojang.logging.LogUtils;
import kiwiapollo.fcgymbadges.commands.GymBadgeCommands;
import kiwiapollo.fcgymbadges.gymbadges.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;

public class FractalCoffeeGymBadges implements ModInitializer {
    public static final String NAMESPACE = "fcgymbadges";
    public static final GymBadge DARK_TYPE_GYM_BADGE = new DarkTypeGymBadge();
    public static final GymBadge LEAF_TYPE_GYM_BADGE = new LeafTypeGymBadge();
    public static final GymBadge FLYING_TYPE_GYM_BADGE = new FlyingTypeGymBadge();
    public static final GymBadge ROCK_TYPE_GYM_BADGE = new RockTypeGymBadge();
    public static final GymBadgeItemGroup GYM_BADGE_ITEM_GROUP = new GymBadgeItemGroup();
    public static final GymBadgeCommands GYM_BADGE_COMMANDS = new GymBadgeCommands();
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        addGymBadgesToItemGroup();
        registerCommands();
    }

    private void addGymBadgesToItemGroup() {
        GYM_BADGE_ITEM_GROUP.addGymBadge(DARK_TYPE_GYM_BADGE);
        GYM_BADGE_ITEM_GROUP.addGymBadge(LEAF_TYPE_GYM_BADGE);
        GYM_BADGE_ITEM_GROUP.addGymBadge(FLYING_TYPE_GYM_BADGE);
        GYM_BADGE_ITEM_GROUP.addGymBadge(ROCK_TYPE_GYM_BADGE);
    }

    private void registerCommands() {
        CommandRegistrationCallback.EVENT.register(GYM_BADGE_COMMANDS::register);
    }
}
