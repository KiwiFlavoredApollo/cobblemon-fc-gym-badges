package kiwiapollo.fcgymbadges.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import kiwiapollo.fcgymbadges.exceptions.PlayerGymBadgeExistException;
import kiwiapollo.fcgymbadges.exceptions.PlayerGymBadgeNotExistException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class ClearGymBadgeCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        try {
            assertExistPlayerGymBadge(context);
            clearPlayerGymBadge(context);
            sendClearPlayerGymBadgeSuccessfulMessage(context);
            return Command.SINGLE_SUCCESS;
        } catch (PlayerGymBadgeNotExistException e) {
            sendClearPlayerGymBadgeErrorMessage(context);
            return -1;
        }
    }

    private void assertExistPlayerGymBadge(CommandContext<ServerCommandSource> context)
            throws PlayerGymBadgeNotExistException {
//        throw(new PlayerGymBadgeNotExistException());
    }

    private void clearPlayerGymBadge(CommandContext<ServerCommandSource> context) {

    }

    private void sendClearPlayerGymBadgeSuccessfulMessage(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        String gymBadge = getGymBadgeArgument(context);
        context.getSource().sendMessage(
                Text.literal(String.format("Cleared %s from %s",
                        gymBadge, player.getGameProfile().getName())));
    }

    private void sendClearPlayerGymBadgeErrorMessage(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        String gymBadge = getGymBadgeArgument(context);
        context.getSource().sendError(
                Text.literal(String.format("Failed to clear %s from %s",
                        gymBadge, player.getGameProfile().getName())));
    }

    private ServerPlayerEntity getPlayerArgument(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {
        return EntityArgumentType.getPlayer(context, "player");
    }

    private String getGymBadgeArgument(CommandContext<ServerCommandSource> context) {
        return StringArgumentType.getString(context, "gymbadge");
    }
}
