package kiwiapollo.fcgymbadges.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import kiwiapollo.fcgymbadges.exceptions.AvailablePlayerInventorySlotNotExistException;
import kiwiapollo.fcgymbadges.exceptions.NotExecutedByPlayerException;
import kiwiapollo.fcgymbadges.gymbadges.GymBadge;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class CreateGymBadgeCommand implements Command<ServerCommandSource> {
    private final GymBadge gymBadge;

    public CreateGymBadgeCommand(GymBadge gymBadge) {
        this.gymBadge = gymBadge;
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) {
        try {
            assertExecutedByPlayer(context);

            ServerPlayerEntity player = context.getSource().getPlayer();
            ItemStack itemStack = new ItemStack(gymBadge.item, 1);
            assertExistAvailablePlayerInventorySlot(player, itemStack);

            player.getInventory().insertStack(itemStack);
            FractalCoffeeGymBadges.LOGGER.info(
                    String.format(
                            "%s created %s",
                            player.getGameProfile().getName(),
                            gymBadge.identifier.toString()
                    )
            );

            return Command.SINGLE_SUCCESS;

        } catch (NotExecutedByPlayerException e) {
            context.getSource().sendError(Text.literal("Command should be run by a player"));
            return -1;

        } catch (AvailablePlayerInventorySlotNotExistException e) {
            context.getSource().sendError(Text.literal("Your inventory is full"));
            FractalCoffeeGymBadges.LOGGER.debug(
                    String.format(
                            "%s does not have available inventory slot",
                            context.getSource().getPlayer().getGameProfile().getName()
                    )
            );
            return -1;
        }
    }

    private void assertExecutedByPlayer(CommandContext<ServerCommandSource> context)
            throws NotExecutedByPlayerException {
        if(!context.getSource().isExecutedByPlayer()) {
            throw new NotExecutedByPlayerException();
        }
    }

    private void assertExistAvailablePlayerInventorySlot(ServerPlayerEntity player, ItemStack itemStack)
            throws AvailablePlayerInventorySlotNotExistException {
        if(player.getInventory().main.stream().anyMatch(
                ItemStack::isEmpty
        )) {
            return;
        }

        if(player.getInventory().main.stream().anyMatch(
                slotStack -> slotStack.getMaxCount() - slotStack.getCount() > itemStack.getCount()
        )) {
            return;
        }

        throw new AvailablePlayerInventorySlotNotExistException();
    }
}
