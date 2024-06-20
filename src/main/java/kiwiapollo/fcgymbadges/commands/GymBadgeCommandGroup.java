package kiwiapollo.fcgymbadges.commands;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;

public class GymBadgeCommandGroup {
    public void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                dispatcher.register(CommandManager.literal("fcgymbadges")
                        .executes(new PrintGymBadgeProgressCommand())
                        .then(new GymBadgeCommand(FractalCoffeeGymBadges.DARK_BADGE).getGymBadgeArgumentBuilder())
                        .then(new GymBadgeCommand(FractalCoffeeGymBadges.LEAF_BADGE).getGymBadgeArgumentBuilder())
                        .then(new GymBadgeCommand(FractalCoffeeGymBadges.FLYING_BADGE).getGymBadgeArgumentBuilder())
                        .then(new GymBadgeCommand(FractalCoffeeGymBadges.ROCK_BADGE).getGymBadgeArgumentBuilder())
                        .then(new GymBadgeCommand(FractalCoffeeGymBadges.ELECTRIC_BADGE).getGymBadgeArgumentBuilder())
                        .then(new GymBadgeCommand(FractalCoffeeGymBadges.FIRE_BADGE).getGymBadgeArgumentBuilder())
                        .then(new GymBadgeCommand(FractalCoffeeGymBadges.ICE_BADGE).getGymBadgeArgumentBuilder())
                        .then(new GymBadgeCommand(FractalCoffeeGymBadges.WATER_BADGE).getGymBadgeArgumentBuilder())
                        .then(new GymBadgeCommand(FractalCoffeeGymBadges.DRAGON_BADGE).getGymBadgeArgumentBuilder())
                )
        );
    }
}
