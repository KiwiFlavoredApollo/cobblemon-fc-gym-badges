package kiwiapollo.fcgymbadges.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import kiwiapollo.fcgymbadges.gymbadges.GymBadge;
import kiwiapollo.fcgymbadges.utilities.CaseConverter;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class GymBadgeCommand extends LiteralArgumentBuilder<ServerCommandSource> {
    private final GymBadge gymBadge;

    public GymBadgeCommand(GymBadge gymBadge) {
        super(CaseConverter.snakeToLower(gymBadge.item.toString()));
        this.gymBadge = gymBadge;

        this.requires(new GymBadgeCommandPredicate(
                String.format("%s.%s",
                        FractalCoffeeGymBadges.NAMESPACE,
                        getLiteral()
                ),
                String.format("%s.%s.create",
                        FractalCoffeeGymBadges.NAMESPACE,
                        getLiteral()
                )
        ))
        .then(getCreateGymBadgeSubcommand());
    }

    private LiteralArgumentBuilder<ServerCommandSource> getCreateGymBadgeSubcommand() {
        return CommandManager.literal("create")
                .requires(new GymBadgeCommandPredicate(
                        String.format("%s.%s.create",
                                FractalCoffeeGymBadges.NAMESPACE,
                                getLiteral()
                        )
                ))
                .executes(new CreateGymBadgeCommand(gymBadge));
    }
}
