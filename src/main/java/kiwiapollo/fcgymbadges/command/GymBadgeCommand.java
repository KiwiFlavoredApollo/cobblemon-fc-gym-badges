package kiwiapollo.fcgymbadges.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import kiwiapollo.fcgymbadges.FCGymBadges;
import kiwiapollo.fcgymbadges.item.GymBadges;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class GymBadgeCommand extends LiteralArgumentBuilder<ServerCommandSource> {
    private final GymBadges gymBadge;

    public GymBadgeCommand(GymBadges gymBadge) {
        super(toCommandName(gymBadge.getIdentifier().getPath()));
        this.gymBadge = gymBadge;

        this.requires(new PlayerCommandSourcePredicate(String.format("%s.%s.create", FCGymBadges.MOD_ID, getLiteral())))
                .then(LiteralArgumentBuilder.<ServerCommandSource>literal("create")
                        .executes(this::create));
    }

    public int create(CommandContext<ServerCommandSource> context) {
        ServerPlayerEntity player = context.getSource().getPlayer();

        if (!FCGymBadges.economy.hasBalance(player, FCGymBadges.config.gymBadgeCreatePrice)) {
            player.sendMessage(Text.translatable("command.fcgymbadges.create_gymbadge.error.not_enough_balance").formatted(Formatting.RED));
            return -1;
        }

        FCGymBadges.economy.removeBalance(player, FCGymBadges.config.gymBadgeCreatePrice);

        ItemStack itemStack = new ItemStack(gymBadge.getItem(), 1);
        if (player.getInventory().insertStack(itemStack)) {
            player.dropStack(itemStack);
        }

        FCGymBadges.LOGGER.info("{} created {}", player.getGameProfile().getName(), gymBadge.getItem().getName());

        return Command.SINGLE_SUCCESS;
    }

    public static String toCommandName(String string) {
        return string.replaceAll("_", "").toLowerCase();
    }
}
