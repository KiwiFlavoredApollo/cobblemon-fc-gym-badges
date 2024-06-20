package kiwiapollo.fcgymbadges.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import kiwiapollo.fcgymbadges.gymbadges.GymBadge;
import kiwiapollo.fcgymbadges.utilities.CaseConverter;
import net.minecraft.command.EntitySelector;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class GymBadgeCommand {
    private final GymBadge gymBadge;

    GymBadgeCommand(GymBadge gymBadge) {
        this.gymBadge = gymBadge;
    }

    public LiteralArgumentBuilder<ServerCommandSource> getGymBadgeArgumentBuilder() {
        return CommandManager.literal(CaseConverter.snakeToLower(this.gymBadge.getName()))
                .requires(new GymBadgeCommandPredicate(
                        String.format("%s.%s.give",
                                FractalCoffeeGymBadges.NAMESPACE,
                                CaseConverter.snakeToLower(this.gymBadge.getName())
                        ),
                        String.format("%s.%s.clear",
                                FractalCoffeeGymBadges.NAMESPACE,
                                CaseConverter.snakeToLower(this.gymBadge.getName())
                        )
                ))
                .then(getGiveBadgeArgumentBuilder())
                .then(getClearBadgeArgumentBuilder());
    }

    private LiteralArgumentBuilder<ServerCommandSource> getGiveBadgeArgumentBuilder() {
        return CommandManager.literal("give")
                .requires(new GymBadgeCommandPredicate(
                        String.format("%s.%s.give",
                                FractalCoffeeGymBadges.NAMESPACE,
                                CaseConverter.snakeToLower(this.gymBadge.getName())
                        )
                ))
                .then(RequiredArgumentBuilder
                        .<ServerCommandSource, EntitySelector>argument(
                                "player", EntityArgumentType.player()
                        )
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new GiveGymBadgeCommand(this.gymBadge))
                );
    }

    private LiteralArgumentBuilder<ServerCommandSource> getClearBadgeArgumentBuilder() {
        return CommandManager.literal("clear")
                .requires(new GymBadgeCommandPredicate(
                        String.format("%s.%s.clear",
                                FractalCoffeeGymBadges.NAMESPACE,
                                CaseConverter.snakeToLower(this.gymBadge.getName())
                        )
                ))
                .then(RequiredArgumentBuilder
                        .<ServerCommandSource, EntitySelector>argument(
                                "player", EntityArgumentType.player()
                        )
                        .suggests(new PlayerArgumentSuggestionProvider())
                        .executes(new ClearGymBadgeCommand(this.gymBadge))
                );
    }
}
