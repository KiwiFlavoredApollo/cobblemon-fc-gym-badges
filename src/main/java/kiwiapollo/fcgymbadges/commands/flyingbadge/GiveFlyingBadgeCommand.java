package kiwiapollo.fcgymbadges.commands.flyingbadge;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import kiwiapollo.fcgymbadges.commands.GiveGymBadgeCommand;
import kiwiapollo.fcgymbadges.exceptions.PlayerGymBadgeExistException;
import kiwiapollo.fcgymbadges.gymbadges.GymBadgeCase;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class GiveFlyingBadgeCommand extends GiveGymBadgeCommand {
    public GiveFlyingBadgeCommand() {
        super("Flying Badge");
    }

    @Override
    protected void giveGymBadgeToPlayer(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        GymBadgeCase gymBadgeCase = new GymBadgeCase(player);
        gymBadgeCase.addFlyingBadge();
        gymBadgeCase.save();
    }

    @Override
    protected void assertNotExistPlayerBadge(CommandContext<ServerCommandSource> context)
            throws PlayerGymBadgeExistException, CommandSyntaxException {

        ServerPlayerEntity player = getPlayerArgument(context);
        GymBadgeCase gymBadgeCase = new GymBadgeCase(player);

        if(gymBadgeCase.isExistFlyingBadge()) {
            throw(new PlayerGymBadgeExistException());
        }
    }
}
