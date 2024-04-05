package kiwiapollo.fcgymbadges.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import kiwiapollo.fcgymbadges.exceptions.PlayerGymBadgeNotExistException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public abstract class ClearGymBadgeCommand implements Command<ServerCommandSource> {
    private final String gymBadgeName;

    public ClearGymBadgeCommand(String gymBadgeName) {
        this.gymBadgeName = gymBadgeName;
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        try {
            assertExistPlayerGymBadge(context);
            clearPlayerGymBadge(context);
            sendClearPlayerGymBadgeSuccessMessage(context);
            return Command.SINGLE_SUCCESS;
        } catch (PlayerGymBadgeNotExistException e) {
            sendClearPlayerGymBadgeErrorMessage(context);
            sendPlayerGymBadgeNotExistMessage(context);
            return -1;
        }
    }

    protected abstract void assertExistPlayerGymBadge(CommandContext<ServerCommandSource> context)
            throws PlayerGymBadgeNotExistException, CommandSyntaxException;

    protected abstract void clearPlayerGymBadge(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException;

    private void sendClearPlayerGymBadgeSuccessMessage(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        String message = getSuccessMessage(player.getGameProfile().getName(), gymBadgeName);
        context.getSource().sendMessage(Text.literal(message));
    }

    private void sendClearPlayerGymBadgeErrorMessage(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        String message = getErrorMessage(player.getGameProfile().getName(), gymBadgeName);
        context.getSource().sendError(Text.literal(message));
    }

    private void sendPlayerGymBadgeNotExistMessage(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        String message = getPlayerGymBadgeNotExistMessage(
                player.getGameProfile().getName(), gymBadgeName);
        context.getSource().sendError(Text.literal(message));
    }

    protected ServerPlayerEntity getPlayerArgument(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {
        return EntityArgumentType.getPlayer(context, "player");
    }

    private String getSuccessMessage(String player, String gymBadge) {
        return String.format("Cleared %s from %s", gymBadge, player);
    }

    private String getErrorMessage(String player, String gymBadge) {
        return String.format("Failed to clear %s from %s", gymBadge, player);
    }

    private String getPlayerGymBadgeNotExistMessage(String player, String gymBadge) {
        return String.format("%s does not have %s", player, gymBadge);
    }
}
