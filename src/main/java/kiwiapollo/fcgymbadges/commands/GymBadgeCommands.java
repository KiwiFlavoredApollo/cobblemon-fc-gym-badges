package kiwiapollo.fcgymbadges.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import kiwiapollo.fcgymbadges.commands.darkbadge.ClearDarkBadgeCommand;
import kiwiapollo.fcgymbadges.commands.darkbadge.GiveDarkBadgeCommand;
import kiwiapollo.fcgymbadges.commands.flyingbadge.ClearFlyingBadgeCommand;
import kiwiapollo.fcgymbadges.commands.flyingbadge.GiveFlyingBadgeCommand;
import kiwiapollo.fcgymbadges.commands.leafbadge.ClearLeafBadgeCommand;
import kiwiapollo.fcgymbadges.commands.leafbadge.GiveLeafBadgeCommand;
import kiwiapollo.fcgymbadges.commands.predicates.*;
import kiwiapollo.fcgymbadges.commands.rockbadge.ClearRockBadgeCommand;
import kiwiapollo.fcgymbadges.commands.rockbadge.GiveRockBadgeCommand;
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
                .executes(new OpenGymBadgeCaseCommand())
                .then(getDarkBadgeCommandBuilder())
                .then(getLeafBadgeCommandBuilder())
                .then(getFlyingBadgeCommandBuilder())
                .then(getRockBadgeCommandBuilder())
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
                        .executes(new GiveDarkBadgeCommand())
                );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getClearDarkBadgeCommandBuilder() {
        return CommandManager.literal("clear")
                .requires(new ClearDarkBadgeCommandPredicate())
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector>argument(
                        "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new ClearDarkBadgeCommand())
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
                        .executes(new GiveLeafBadgeCommand())
                );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getClearLeafBadgeCommandBuilder() {
        return CommandManager.literal("clear")
                .requires(new ClearLeafBadgeCommandPredicate())
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector>argument(
                        "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new ClearLeafBadgeCommand())
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
                        .executes(new GiveFlyingBadgeCommand())
                );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getClearFlyingBadgeCommandBuilder() {
        return CommandManager.literal("clear")
                .requires(new ClearFlyingBadgeCommandPredicate())
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector>argument(
                                "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new ClearFlyingBadgeCommand())
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
                        .executes(new GiveRockBadgeCommand())
                );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getClearRockBadgeCommandBuilder() {
        return CommandManager.literal("clear")
                .requires(new ClearRockBadgeCommandPredicate())
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector>argument(
                                "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new ClearRockBadgeCommand())
                );
    }
}
