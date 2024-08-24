package kiwiapollo.fcgymbadges.economies;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class Vanilla implements Economy {
    private static final Item currencyItem =
            Registries.ITEM.get(new Identifier(FractalCoffeeGymBadges.CONFIG.vanillaCurrency));
    @Override
    public double getBalance(ServerPlayerEntity player) {
        int count = 0;

        // Check the main inventory
        for (ItemStack stack : player.getInventory().main) {
            if (stack.getItem() == currencyItem) {
                count += stack.getCount();
            }
        }

        // Check the offhand
        ItemStack offhandStack = player.getInventory().offHand.get(0);
        if (offhandStack.getItem() == currencyItem) {
            count += offhandStack.getCount();
        }

        return count;
    }

    @Override
    public void addBalance(ServerPlayerEntity player, double amount) {
        player.getInventory().offerOrDrop(new ItemStack(currencyItem, (int) amount));
    }

    @Override
    public void removeBalance(ServerPlayerEntity player, double amount) {
        int remaining = (int) amount;

        // Iterate through the player's main inventory
        for (int i = 0; i < player.getInventory().main.size(); i++) {
            ItemStack stack = player.getInventory().main.get(i);
            if (stack.getItem() == currencyItem) {
                int stackCount = stack.getCount();

                if (stackCount <= remaining) {
                    remaining -= stackCount;
                    player.getInventory().removeStack(i);

                } else {
                    stack.decrement(remaining);
                    remaining = 0;
                }

                if (remaining <= 0) {
                    break;
                }
            }
        }

        // Check the offhand slot if still necessary
        if (remaining <= 0) {
            return;
        }

        ItemStack offhandStack = player.getInventory().offHand.get(0);
        if (offhandStack.getItem() == currencyItem) {
            int stackCount = offhandStack.getCount();

            if (stackCount <= remaining) {
                remaining -= stackCount;
                int offHand = player.getInventory().size() - 1;
                player.getInventory().removeStack(offHand);

            } else {
                offhandStack.decrement(remaining);
                remaining = 0;
            }
        }
    }

    @Override
    public boolean isExistEnoughBalance(ServerPlayerEntity player, double amount) {
        return getBalance(player) >= amount;
    }
}
