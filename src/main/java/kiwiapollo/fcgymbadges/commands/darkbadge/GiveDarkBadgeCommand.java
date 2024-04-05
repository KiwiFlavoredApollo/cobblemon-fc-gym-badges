package kiwiapollo.fcgymbadges.commands.darkbadge;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import kiwiapollo.fcgymbadges.commands.GiveGymBadgeCommand;
import kiwiapollo.fcgymbadges.exceptions.PlayerGymBadgeExistException;
import kiwiapollo.fcgymbadges.gymbadges.GymBadgeCase;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class GiveDarkBadgeCommand extends GiveGymBadgeCommand {
    public GiveDarkBadgeCommand() {
        super("Dark Badge");
    }

    @Override
    protected void giveGymBadgeToPlayer(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        GymBadgeCase gymBadgeCase = new GymBadgeCase(player);
        gymBadgeCase.addDarkBadge();
        gymBadgeCase.save();
    }

    @Override
    protected void assertNotExistPlayerBadge(CommandContext<ServerCommandSource> context)
            throws PlayerGymBadgeExistException, CommandSyntaxException {

        ServerPlayerEntity player = getPlayerArgument(context);
        GymBadgeCase gymBadgeCase = new GymBadgeCase(player);

        if(gymBadgeCase.isExistDarkBadge()) {
            throw(new PlayerGymBadgeExistException());
        }
    }
}
