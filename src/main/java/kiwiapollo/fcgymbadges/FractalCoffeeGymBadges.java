package kiwiapollo.fcgymbadges;

import kiwiapollo.fcgymbadges.commands.FractalCoffeeGymBadgeCommands;
import kiwiapollo.fcgymbadges.gymbadges.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class FractalCoffeeGymBadges implements ModInitializer {

    public static final String NAMESPACE = "fcgymbadges";
    public static final GymBadge darkTypeGymBadge = new DarkTypeGymBadge();
    public static final GymBadge leafTypeGymBadge = new LeafTypeGymBadge();
    public static final GymBadge flyingTypeGymBadge = new FlyingTypeGymBadge();
    public static final GymBadge rockTypeGymBadge = new RockTypeGymBadge();
    public static final GymBadgeItemGroup gymBadgeItemGroup = new GymBadgeItemGroup();
    public static final FractalCoffeeGymBadgeCommands commands = new FractalCoffeeGymBadgeCommands();

    @Override
    public void onInitialize() {
        registerCommands();
        registerItems();
    }

    private void registerItems() {
        gymBadgeItemGroup.addGymBadge(darkTypeGymBadge);
        gymBadgeItemGroup.addGymBadge(leafTypeGymBadge);
        gymBadgeItemGroup.addGymBadge(flyingTypeGymBadge);
        gymBadgeItemGroup.addGymBadge(rockTypeGymBadge);
    }

    private void registerCommands() {
        CommandRegistrationCallback.EVENT.register(commands::register);
    }
}
