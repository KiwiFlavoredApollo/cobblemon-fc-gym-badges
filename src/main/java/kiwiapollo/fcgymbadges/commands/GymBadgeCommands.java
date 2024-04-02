package kiwiapollo.fcgymbadges.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import kiwiapollo.fcgymbadges.commands.suggestion.GymBadgeSuggestionProvider;
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
                .then(getGiveGymBadgeCommandBuilder())
                .then(getClearGymBadgeCommandBuilder())
        );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getGiveGymBadgeCommandBuilder() {
        return CommandManager.literal("give")
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector> argument(
                        "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .then(RequiredArgumentBuilder.<ServerCommandSource, String> argument(
                                "gymbadge", StringArgumentType.string())
                                .suggests(new GymBadgeSuggestionProvider())
                                .executes(new GiveGymBadgeCommand())
                        )
                );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getClearGymBadgeCommandBuilder() {
        return CommandManager.literal("clear")
                .then(RequiredArgumentBuilder.<ServerCommandSource, EntitySelector> argument(
                        "player", EntityArgumentType.player())
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .then(RequiredArgumentBuilder.<ServerCommandSource, String> argument(
                                "gymbadge", StringArgumentType.string())
                                .suggests(new GymBadgeSuggestionProvider())
                                .executes(new ClearGymBadgeCommand())
                        )
                );
    }
}
