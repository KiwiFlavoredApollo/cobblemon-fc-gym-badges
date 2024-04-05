package kiwiapollo.fcgymbadges.commands.leafbadge;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import kiwiapollo.fcgymbadges.commands.ClearGymBadgeCommand;
import kiwiapollo.fcgymbadges.exceptions.PlayerGymBadgeNotExistException;
import kiwiapollo.fcgymbadges.storage.GymBadgeCase;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class ClearLeafBadgeCommand extends ClearGymBadgeCommand {
    public ClearLeafBadgeCommand() {
        super("Leaf Badge");
    }

    @Override
    protected void assertExistPlayerGymBadge(CommandContext<ServerCommandSource> context)
            throws PlayerGymBadgeNotExistException, CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        GymBadgeCase gymBadgeCase = new GymBadgeCase(player);

        if(!gymBadgeCase.isExistLeafBadge()) {
            throw(new PlayerGymBadgeNotExistException());
        }
    }

    @Override
    protected void clearPlayerGymBadge(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        GymBadgeCase gymBadgeCase = new GymBadgeCase(player);
        gymBadgeCase.removeLeafBadge();
        gymBadgeCase.save();
    }
}
