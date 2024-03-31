package kiwiapollo.fcgymbadges.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import kiwiapollo.fcgymbadges.storage.GymBadgeCase;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class OpenGymBadgeCaseCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        printGymBadgeReport(context);
        return Command.SINGLE_SUCCESS;
    }

    private void printGymBadgeReport(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = getPlayerArgument(context);
        GymBadgeCase gymBadgeCase = new GymBadgeCase(player);
        context.getSource().sendMessage(Text.literal(String.format("%s's Gym Badge Case", player.getGameProfile().getName())));
        context.getSource().sendMessage(Text.literal(String.format("Dark Type Gym Badge: %s", gymBadgeCase.isExistGymBadge("darkTypeGymBadge"))));
        context.getSource().sendMessage(Text.literal(String.format("Leaf Type Gym Badge: %s", gymBadgeCase.isExistGymBadge("leafTypeGymBadge"))));
        context.getSource().sendMessage(Text.literal(String.format("Flying Type Gym Badge: %s", gymBadgeCase.isExistGymBadge("flyingTypeGymBadge"))));
        context.getSource().sendMessage(Text.literal(String.format("Rock Type Gym Badge: %s", gymBadgeCase.isExistGymBadge("rockTypeGymBadge"))));
    }

    private ServerPlayerEntity getPlayerArgument(CommandContext<ServerCommandSource> context)
            throws CommandSyntaxException {

        return context.getSource().getPlayer();
    }
}
