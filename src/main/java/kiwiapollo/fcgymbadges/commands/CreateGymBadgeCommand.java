package kiwiapollo.fcgymbadges.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import kiwiapollo.fcgymbadges.economies.Economy;
import kiwiapollo.fcgymbadges.economies.Vanilla;
import kiwiapollo.fcgymbadges.exceptions.AvailablePlayerInventorySlotNotExistException;
import kiwiapollo.fcgymbadges.exceptions.NotEnoughBalanceException;
import kiwiapollo.fcgymbadges.exceptions.NotExecutedByPlayerException;
import kiwiapollo.fcgymbadges.gymbadges.GymBadge;
import kiwiapollo.fcgymbadges.utilities.EconomyFactory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

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

            assertPlayerExistEnoughBalance(player, FractalCoffeeGymBadges.CONFIG.gymBadgeCreatePrice);
            EconomyFactory.create(FractalCoffeeGymBadges.CONFIG.economy)
                    .removeBalance(player, FractalCoffeeGymBadges.CONFIG.gymBadgeCreatePrice);

            player.getInventory().insertStack(itemStack);
            FractalCoffeeGymBadges.LOGGER.info(
                    String.format(
                            "%s created %s",
                            player.getGameProfile().getName(),
                            gymBadge.item.getName().getString()
                    )
            );

            return Command.SINGLE_SUCCESS;

        } catch (NotExecutedByPlayerException e) {
            context.getSource().sendError(Text.literal("Command should be run by a player"));
            return -1;

        } catch (NotEnoughBalanceException e) {
            context.getSource().sendError(Text.literal(getNotEnoughBalanceMessage()));
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
        if (!context.getSource().isExecutedByPlayer()) {
            throw new NotExecutedByPlayerException();
        }
    }

    private void assertPlayerExistEnoughBalance(ServerPlayerEntity player, double amount)
            throws NotEnoughBalanceException {
        if (!EconomyFactory.create(FractalCoffeeGymBadges.CONFIG.economy)
                .isExistEnoughBalance(player, amount)) {
            throw new NotEnoughBalanceException();
        }
    }

    private void assertExistAvailablePlayerInventorySlot(ServerPlayerEntity player, ItemStack itemStack)
            throws AvailablePlayerInventorySlotNotExistException {
        if (player.getInventory().main.stream().anyMatch(ItemStack::isEmpty)) {
            return;
        }

        if (player.getInventory().main.stream().anyMatch(slotStack -> canAddStack(slotStack, itemStack))) {
            return;
        }

        throw new AvailablePlayerInventorySlotNotExistException();
    }

    private boolean canAddStack(ItemStack slotStack, ItemStack itemStack) {
        return slotStack.getItem() == itemStack.getItem()
                && slotStack.getMaxCount() - slotStack.getCount() > itemStack.getCount();
    }

    private String getNotEnoughBalanceMessage() {
        Economy economy = EconomyFactory.create(FractalCoffeeGymBadges.CONFIG.economy);
        if (economy instanceof Vanilla) {
            return String.format(
                    "Not enough item: %s x%d",
                    Registries.ITEM.get(new Identifier(
                            FractalCoffeeGymBadges.CONFIG.vanillaCurrency)).getName().getString(),
                    (int) Math.floor(FractalCoffeeGymBadges.CONFIG.gymBadgeCreatePrice)
            );

        } else {
            return String.format(
                    "Not enough balance: $%f",
                    FractalCoffeeGymBadges.CONFIG.gymBadgeCreatePrice
            );
        }
    }
}
