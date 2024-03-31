package kiwiapollo.fcgymbadges.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import kiwiapollo.fcgymbadges.exceptions.PlayerGymBadgeNotExistException;
import kiwiapollo.fcgymbadges.storage.GymBadgeCase;
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
            FractalCoffeeGymBadges.logger.debug(e.getMessage());
            sendClearPlayerGymBadgeErrorMessage(context);
            return -1;
        }
    }

    private void assertExistPlayerGymBadge(CommandContext<ServerCommandSource> context)
            throws PlayerGymBadgeNotExistException, CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        String gymBadge = getGymBadgeArgument(context);
        GymBadgeCase gymBadgeCase = new GymBadgeCase(player);

        if(!gymBadgeCase.isExistGymBadge(gymBadge)) {
            String message = String.format("%s does not have %s", player.getGameProfile().getName(), gymBadge);
            throw(new PlayerGymBadgeNotExistException(message));
        }
    }

    private void clearPlayerGymBadge(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        String gymBadge = getGymBadgeArgument(context);
        GymBadgeCase gymBadgeCase = new GymBadgeCase(player);
        gymBadgeCase.removeGymBadge(gymBadge);
        gymBadgeCase.save();
    }

    private void sendClearPlayerGymBadgeSuccessfulMessage(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        String gymBadge = getGymBadgeArgument(context);
        String message = String.format("Cleared %s from %s", gymBadge, player.getGameProfile().getName());
        context.getSource().sendMessage(Text.literal(message));
    }

    private void sendClearPlayerGymBadgeErrorMessage(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        String gymBadge = getGymBadgeArgument(context);
        String message = String.format("Failed to clear %s from %s", gymBadge, player.getGameProfile().getName());
        context.getSource().sendError(Text.literal(message));
    }

    private ServerPlayerEntity getPlayerArgument(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {
        return EntityArgumentType.getPlayer(context, "player");
    }

    private String getGymBadgeArgument(CommandContext<ServerCommandSource> context) {
        return StringArgumentType.getString(context, "gymbadge");
    }
}
