package kiwiapollo.fcgymbadges.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import kiwiapollo.fcgymbadges.gymbadges.GymBadge;
import kiwiapollo.fcgymbadges.gymbadges.GymBadgeProgress;
import kiwiapollo.fcgymbadges.utilities.CaseConverter;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class PrintGymBadgeProgressCommand implements Command<ServerCommandSource> {
    @Override
    public int run(CommandContext<ServerCommandSource> context) {
        printGymBadgeReport(context);
        return Command.SINGLE_SUCCESS;
    }

    private void printGymBadgeReport(CommandContext<ServerCommandSource> context) {
        printGymBadgeProgressTitle(context);

        printGymBadgeExistence(context, FractalCoffeeGymBadges.DARK_BADGE);
        printGymBadgeExistence(context, FractalCoffeeGymBadges.LEAF_BADGE);
        printGymBadgeExistence(context, FractalCoffeeGymBadges.FLYING_BADGE);
        printGymBadgeExistence(context, FractalCoffeeGymBadges.ROCK_BADGE);
        printGymBadgeExistence(context, FractalCoffeeGymBadges.ELECTRIC_BADGE);
        printGymBadgeExistence(context, FractalCoffeeGymBadges.FIRE_BADGE);
        printGymBadgeExistence(context, FractalCoffeeGymBadges.ICE_BADGE);
        printGymBadgeExistence(context, FractalCoffeeGymBadges.WATER_BADGE);
        printGymBadgeExistence(context, FractalCoffeeGymBadges.DRAGON_BADGE);
        printGymBadgeExistence(context, FractalCoffeeGymBadges.GROUND_BADGE);
        printGymBadgeExistence(context, FractalCoffeeGymBadges.PSYCHIC_BADGE);
        printGymBadgeExistence(context, FractalCoffeeGymBadges.GHOST_BADGE);
        printGymBadgeExistence(context, FractalCoffeeGymBadges.WING_BADGE);
        printGymBadgeExistence(context, FractalCoffeeGymBadges.FOUNTAIN_BADGE);
    }

    private ServerPlayerEntity getPlayerArgument(CommandContext<ServerCommandSource> context) {
        return context.getSource().getPlayer();
    }

    private void printGymBadgeProgressTitle(CommandContext<ServerCommandSource> context) {
        ServerPlayerEntity player = getPlayerArgument(context);
        Text message = Text.literal(String.format("%s's Gym Badges", player.getGameProfile().getName()))
                .formatted(Formatting.YELLOW);
        context.getSource().sendMessage(message);
    }

    private void printGymBadgeExistence(CommandContext<ServerCommandSource> context, GymBadge gymBadge) {
        ServerPlayerEntity player = getPlayerArgument(context);
        GymBadgeProgress gymBadgeProgress = new GymBadgeProgress(player);
        boolean isExistGymBadge = gymBadgeProgress.isExistGymBadge(gymBadge);

        Text message = Text.literal(CaseConverter.snakeToDisplay(gymBadge.getName()))
                .formatted(getFormattingByGymBadgeExistence(isExistGymBadge));
        context.getSource().sendMessage(message);
    }

    private Formatting getFormattingByGymBadgeExistence(boolean isExist) {
        return isExist ? Formatting.DARK_GREEN : Formatting.DARK_GRAY;
    }
}
