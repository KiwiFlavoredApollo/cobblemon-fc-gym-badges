package kiwiapollo.fcgymbadges.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import kiwiapollo.fcgymbadges.commands.predicates.*;
import kiwiapollo.fcgymbadges.commands.suggestion.PlayerArgumentSuggestionProvider;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.EntitySelector;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class GymBadgeCommands {
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess,
                         CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("fcgymbadges")
                .executes(new PrintGymBadgeProgressCommand())
                .then(getDarkBadgeCommandBuilder())
                .then(getLeafBadgeCommandBuilder())
                .then(getFlyingBadgeCommandBuilder())
                .then(getRockBadgeCommandBuilder())
                .then(getElectricBadgeCommandBuilder())
                .then(getFireBadgeCommandBuilder())
        );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getDarkBadgeCommandBuilder() {
        return CommandManager.literal("darkbadge")
                .then(getGiveDarkBadgeCommandBuilder())
                .then(getClearDarkBadgeCommandBuilder());
    }

    private LiteralArgumentBuilder<ServerCommandSource> getGiveDarkBadgeCommandBuilder() {
        return CommandManager.literal("give")
                .requires(new GiveDarkBadgeCommandPredicate())
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector>argument(
                        "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new GiveGymBadgeCommand(FractalCoffeeGymBadges.DARK_BADGE))
                );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getClearDarkBadgeCommandBuilder() {
        return CommandManager.literal("clear")
                .requires(new ClearDarkBadgeCommandPredicate())
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector>argument(
                        "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new ClearGymBadgeCommand(FractalCoffeeGymBadges.DARK_BADGE))
                );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getLeafBadgeCommandBuilder() {
        return CommandManager.literal("leafbadge")
                .then(getGiveLeafBadgeCommandBuilder())
                .then(getClearLeafBadgeCommandBuilder());
    }

    private LiteralArgumentBuilder<ServerCommandSource> getGiveLeafBadgeCommandBuilder() {
        return CommandManager.literal("give")
                .requires(new GiveLeafBadgeCommandPredicate())
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector>argument(
                        "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new GiveGymBadgeCommand(FractalCoffeeGymBadges.LEAF_BADGE))
                );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getClearLeafBadgeCommandBuilder() {
        return CommandManager.literal("clear")
                .requires(new ClearLeafBadgeCommandPredicate())
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector>argument(
                        "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new ClearGymBadgeCommand(FractalCoffeeGymBadges.LEAF_BADGE))
                );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getFlyingBadgeCommandBuilder() {
        return CommandManager.literal("flyingbadge")
                .then(getGiveFlyingBadgeCommandBuilder())
                .then(getClearFlyingBadgeCommandBuilder());
    }

    private LiteralArgumentBuilder<ServerCommandSource> getGiveFlyingBadgeCommandBuilder() {
        return CommandManager.literal("give")
                .requires(new GiveFlyingBadgeCommandPredicate())
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector>argument(
                                "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new GiveGymBadgeCommand(FractalCoffeeGymBadges.FLYING_BADGE))
                );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getClearFlyingBadgeCommandBuilder() {
        return CommandManager.literal("clear")
                .requires(new ClearFlyingBadgeCommandPredicate())
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector>argument(
                                "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new ClearGymBadgeCommand(FractalCoffeeGymBadges.FLYING_BADGE))
                );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getRockBadgeCommandBuilder() {
        return CommandManager.literal("rockbadge")
                .then(getGiveRockBadgeCommandBuilder())
                .then(getClearRockBadgeCommandBuilder());
    }

    private LiteralArgumentBuilder<ServerCommandSource> getGiveRockBadgeCommandBuilder() {
        return CommandManager.literal("give")
                .requires(new GiveRockBadgeCommandPredicate())
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector>argument(
                                "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new GiveGymBadgeCommand(FractalCoffeeGymBadges.ROCK_BADGE))
                );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getClearRockBadgeCommandBuilder() {
        return CommandManager.literal("clear")
                .requires(new ClearRockBadgeCommandPredicate())
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector>argument(
                                "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new ClearGymBadgeCommand(FractalCoffeeGymBadges.ROCK_BADGE))
                );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getElectricBadgeCommandBuilder() {
        return CommandManager.literal("electricbadge")
                .then(getGiveElectricBadgeCommandBuilder())
                .then(getClearElectricBadgeCommandBuilder());
    }

    private LiteralArgumentBuilder<ServerCommandSource> getGiveElectricBadgeCommandBuilder() {
        return CommandManager.literal("give")
                .requires(new GiveElectricBadgeCommandPredicate())
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector>argument(
                                "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new GiveGymBadgeCommand(FractalCoffeeGymBadges.ELECTRIC_BADGE))
                );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getClearElectricBadgeCommandBuilder() {
        return CommandManager.literal("clear")
                .requires(new ClearElectricBadgeCommandPredicate())
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector>argument(
                                "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new ClearGymBadgeCommand(FractalCoffeeGymBadges.ELECTRIC_BADGE))
                );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getFireBadgeCommandBuilder() {
        return CommandManager.literal("firebadge")
                .then(getGiveFireBadgeCommandBuilder())
                .then(getClearFireBadgeCommandBuilder());
    }

    private LiteralArgumentBuilder<ServerCommandSource> getGiveFireBadgeCommandBuilder() {
        return CommandManager.literal("give")
                .requires(new GiveFireBadgeCommandPredicate())
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector>argument(
                                "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new GiveGymBadgeCommand(FractalCoffeeGymBadges.FIRE_BADGE))
                );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getClearFireBadgeCommandBuilder() {
        return CommandManager.literal("clear")
                .requires(new ClearFireBadgeCommandPredicate())
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector>argument(
                                "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new ClearGymBadgeCommand(FractalCoffeeGymBadges.FIRE_BADGE))
                );
    }
}
