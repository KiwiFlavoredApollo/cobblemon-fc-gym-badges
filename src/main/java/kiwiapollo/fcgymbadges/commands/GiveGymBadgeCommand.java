package kiwiapollo.fcgymbadges.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import kiwiapollo.fcgymbadges.exceptions.PlayerGymBadgeExistException;
import kiwiapollo.fcgymbadges.storage.GymBadgeCase;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class GiveGymBadgeCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        try {
            assertNotExistPlayerBadge(context);
            giveGymBadgeToPlayer(context);
            sendGiveGymBadgeSuccessfulMessage(context);
            return Command.SINGLE_SUCCESS;
        } catch (PlayerGymBadgeExistException e) {
            FractalCoffeeGymBadges.logger.debug(e.getMessage());
            sendGiveGymBadgeErrorMessage(context);
            return -1;
        }
    }

    private void giveGymBadgeToPlayer(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        String gymBadge = getGymBadgeArgument(context);
        GymBadgeCase gymBadgeCase = new GymBadgeCase(player);
        gymBadgeCase.addGymBadge(gymBadge);
        gymBadgeCase.save();
    }

    private void assertNotExistPlayerBadge(CommandContext<ServerCommandSource> context)
            throws PlayerGymBadgeExistException, CommandSyntaxException {

        ServerPlayerEntity player = getPlayerArgument(context);
        String gymBadge = getGymBadgeArgument(context);
        GymBadgeCase gymBadgeCase = new GymBadgeCase(player);

        if(gymBadgeCase.isExistGymBadge(gymBadge)) {
            String message = String.format("%s already have %s", player.getGameProfile().getName(), gymBadge);
            throw(new PlayerGymBadgeExistException(message));
        }
    }

    private void sendGiveGymBadgeSuccessfulMessage(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {

        ServerPlayerEntity player = getPlayerArgument(context);
        String gymBadge = getGymBadgeArgument(context);
        String message = String.format("Gave %s to %s", gymBadge, player.getGameProfile().getName());
        context.getSource().sendMessage(Text.literal(message));
    }

    private void sendGiveGymBadgeErrorMessage(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {

        ServerPlayerEntity player = getPlayerArgument(context);
        String gymBadge = getGymBadgeArgument(context);
        String message = String.format("Failed to give %s to %s", gymBadge, player.getGameProfile().getName());
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
