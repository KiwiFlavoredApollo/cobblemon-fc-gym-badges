package kiwiapollo.fcgymbadges.commands.darkbadge;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import kiwiapollo.fcgymbadges.commands.ClearGymBadgeCommand;
import kiwiapollo.fcgymbadges.exceptions.PlayerGymBadgeNotExistException;
import kiwiapollo.fcgymbadges.gymbadges.GymBadgeCase;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class ClearDarkBadgeCommand extends ClearGymBadgeCommand {
    public ClearDarkBadgeCommand() {
        super("Dark Badge");
    }

    @Override
    protected void assertExistPlayerGymBadge(CommandContext<ServerCommandSource> context)
            throws PlayerGymBadgeNotExistException, CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        GymBadgeCase gymBadgeCase = new GymBadgeCase(player);

        if(!gymBadgeCase.isExistDarkBadge()) {
            throw(new PlayerGymBadgeNotExistException());
        }
    }

    @Override
    protected void clearPlayerGymBadge(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        GymBadgeCase gymBadgeCase = new GymBadgeCase(player);
        gymBadgeCase.removeDarkBadge();
        gymBadgeCase.save();
    }
}
