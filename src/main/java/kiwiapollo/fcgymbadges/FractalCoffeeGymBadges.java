package kiwiapollo.fcgymbadges;

import com.mojang.logging.LogUtils;
import kiwiapollo.fcgymbadges.commands.GymBadgeCommandGroup;
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
    public static final GymBadge ICE_BADGE = new IceBadge();
    public static final GymBadge WATER_BADGE = new WaterBadge();
    public static final GymBadge DRAGON_BADGE = new DragonBadge();
    public static final GymBadge GROUND_BADGE = new GroundBadge();
    public static final GymBadge PSYCHIC_BADGE = new PsychicBadge();
    public static final GymBadge GHOST_BADGE = new GhostBadge();
    public static final GymBadgeItemGroup GYM_BADGE_ITEM_GROUP = new GymBadgeItemGroup();
    public static final GymBadgeCommandGroup GYM_BADGE_COMMAND_GROUP = new GymBadgeCommandGroup();
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
        ICE_BADGE.register();
        WATER_BADGE.register();
        DRAGON_BADGE.register();
        GROUND_BADGE.register();
        PSYCHIC_BADGE.register();
        GHOST_BADGE.register();
    }

    private void registerGymBadgeItemGroup() {
        GYM_BADGE_ITEM_GROUP.register();
    }

    private void registerCommands() {
        GYM_BADGE_COMMAND_GROUP.register();
    }
}
