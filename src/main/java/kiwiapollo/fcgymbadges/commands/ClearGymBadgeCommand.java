package kiwiapollo.fcgymbadges.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import kiwiapollo.fcgymbadges.exceptions.PlayerGymBadgeNotExistException;
import kiwiapollo.fcgymbadges.gymbadges.GymBadge;
import kiwiapollo.fcgymbadges.gymbadges.GymBadgeProgress;
import kiwiapollo.fcgymbadges.utilities.CaseConverter;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class ClearGymBadgeCommand implements Command<ServerCommandSource> {
    private final GymBadge gymBadge;

    public ClearGymBadgeCommand(GymBadge gymBadge) {
        this.gymBadge = gymBadge;
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

    protected void assertExistPlayerGymBadge(CommandContext<ServerCommandSource> context)
            throws PlayerGymBadgeNotExistException, CommandSyntaxException {

        ServerPlayerEntity player = getPlayerArgument(context);
        GymBadgeProgress gymBadgeProgress = new GymBadgeProgress(player);

        if(!gymBadgeProgress.isExistGymBadge(gymBadge)) {
            throw(new PlayerGymBadgeNotExistException());
        }
    }

    protected void clearPlayerGymBadge(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {

        ServerPlayerEntity player = getPlayerArgument(context);
        GymBadgeProgress gymBadgeProgress = new GymBadgeProgress(player);
        gymBadgeProgress.removeGymBadge(gymBadge);
        gymBadgeProgress.save();
    }

    private void sendClearPlayerGymBadgeSuccessMessage(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        String message = getSuccessMessage(player.getGameProfile().getName(),
                CaseConverter.snakeToDisplay(gymBadge.getName()));
        context.getSource().sendMessage(Text.literal(message));
    }

    private void sendClearPlayerGymBadgeErrorMessage(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        String message = getErrorMessage(player.getGameProfile().getName(),
                CaseConverter.snakeToDisplay(gymBadge.getName()));
        context.getSource().sendError(Text.literal(message));
    }

    private void sendPlayerGymBadgeNotExistMessage(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        String message = getPlayerGymBadgeNotExistMessage(
                player.getGameProfile().getName(), CaseConverter.snakeToDisplay(gymBadge.getName()));
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
