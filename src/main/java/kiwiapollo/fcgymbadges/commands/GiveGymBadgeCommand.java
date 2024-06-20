package kiwiapollo.fcgymbadges.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import kiwiapollo.fcgymbadges.exceptions.PlayerGymBadgeExistException;
import kiwiapollo.fcgymbadges.gymbadges.GymBadge;
import kiwiapollo.fcgymbadges.gymbadges.GymBadgeProgress;
import kiwiapollo.fcgymbadges.utilities.CaseConverter;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class GiveGymBadgeCommand implements Command<ServerCommandSource> {
    private final GymBadge gymBadge;

    public GiveGymBadgeCommand(GymBadge gymBadge) {
        this.gymBadge = gymBadge;
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        try {
            assertNotExistPlayerBadge(context);
            giveGymBadgeToPlayer(context);
            sendGiveGymBadgeSuccessfulMessage(context);
            return Command.SINGLE_SUCCESS;
            
        } catch (PlayerGymBadgeExistException e) {
            sendGiveGymBadgeErrorMessage(context);
            sendPlayerGymBadgeExistMessage(context);
            return -1;
        }
    }

    private void giveGymBadgeToPlayer(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {

        ServerPlayerEntity player = getPlayerArgument(context);
        GymBadgeProgress gymBadgeProgress = new GymBadgeProgress(player);
        gymBadgeProgress.addGymBadge(gymBadge);
        gymBadgeProgress.save();
    }

    private void assertNotExistPlayerBadge(CommandContext<ServerCommandSource> context)
            throws PlayerGymBadgeExistException, CommandSyntaxException {

        ServerPlayerEntity player = getPlayerArgument(context);
        GymBadgeProgress gymBadgeProgress = new GymBadgeProgress(player);

        if(gymBadgeProgress.isExistGymBadge(gymBadge)) {
            throw(new PlayerGymBadgeExistException());
        }
    }

    private void sendGiveGymBadgeSuccessfulMessage(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {

        ServerPlayerEntity player = getPlayerArgument(context);
        String message = getSuccessMessage(player.getGameProfile().getName(),
                CaseConverter.snakeToDisplay(gymBadge.getName()));
        context.getSource().sendMessage(Text.literal(message));
    }

    private void sendGiveGymBadgeErrorMessage(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {

        ServerPlayerEntity player = getPlayerArgument(context);
        String message = getErrorMessage(player.getGameProfile().getName(),
                CaseConverter.snakeToDisplay(gymBadge.getName()));
        context.getSource().sendError(Text.literal(message));
    }

    private void sendPlayerGymBadgeExistMessage(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {

        ServerPlayerEntity player = getPlayerArgument(context);
        String message = getPlayerGymBadgeExistMessage(
                player.getGameProfile().getName(), CaseConverter.snakeToDisplay(gymBadge.getName()));
        context.getSource().sendError(Text.literal(message));
    }

    protected ServerPlayerEntity getPlayerArgument(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {

        return EntityArgumentType.getPlayer(context, "player");
    }

    private String getSuccessMessage(String player, String gymBadge) {
        return String.format("Gave %s to %s", gymBadge, player);
    }

    private String getErrorMessage(String player, String gymBadge) {
        return String.format("Failed to give %s to %s", gymBadge, player);
    }

    private String getPlayerGymBadgeExistMessage(String player, String gymBadge) {
        return String.format("%s already has %s", player, gymBadge);
    }
}
