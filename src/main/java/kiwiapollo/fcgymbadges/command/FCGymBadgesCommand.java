package kiwiapollo.fcgymbadges.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import kiwiapollo.fcgymbadges.FCGymBadges;
import kiwiapollo.fcgymbadges.item.GymBadges;
import net.minecraft.server.command.ServerCommandSource;

public class FCGymBadgesCommand extends LiteralArgumentBuilder<ServerCommandSource> {
    public FCGymBadgesCommand() {
        super(FCGymBadges.MOD_ID);

        for (GymBadges gymBadge : GymBadges.values()) {
            this.then(new GymBadgeCommand(gymBadge));
        }

        this.then(new ReloadConfigCommand());
    }
}
