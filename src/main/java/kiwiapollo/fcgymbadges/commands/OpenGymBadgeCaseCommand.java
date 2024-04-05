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

    private void printGymBadgeReport(CommandContext<ServerCommandSource> context) {
        ServerPlayerEntity player = getPlayerArgument(context);
        GymBadgeCase gymBadgeCase = new GymBadgeCase(player);

        context.getSource().sendMessage(
                Text.literal(String.format("%s's Gym Badge Case", player.getGameProfile().getName())));

        context.getSource().sendMessage(
                Text.literal(String.format("Dark Badge: %s", gymBadgeCase.isExistDarkBadge())));
        context.getSource().sendMessage(
                Text.literal(String.format("Leaf Badge: %s", gymBadgeCase.isExistLeafBadge())));
        context.getSource().sendMessage(
                Text.literal(String.format("Flying Badge: %s", gymBadgeCase.isExistFlyingBadge())));
        context.getSource().sendMessage(
                Text.literal(String.format("Rock Badge: %s", gymBadgeCase.isExistRockBadge())));
    }

    private ServerPlayerEntity getPlayerArgument(CommandContext<ServerCommandSource> context) {
        return context.getSource().getPlayer();
    }
}
