package kiwiapollo.fcgymbadges.economy;

import kiwiapollo.fcgymbadges.FCGymBadges;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class VanillaEconomy implements Economy {
    private final Item currencyItem;

    public VanillaEconomy() {
        if (!isValidConfiguration()) {
            FCGymBadges.LOGGER.error("Failed to load VanillaEconomy");
            throw new IllegalStateException();
        }

        this.currencyItem = Registries.ITEM.get(Identifier.tryParse(FCGymBadges.config.vanillaCurrencyItem));

        FCGymBadges.LOGGER.info("Loaded VanillaEconomy");
    }

    private boolean isValidConfiguration() {
        return isValidCurrencyItem() && isValidCurrencyAmount();
    }

    private boolean isValidCurrencyItem() {
        int currencyItemCount = (int) Math.floor(FCGymBadges.config.gymBadgeCreatePrice);

        if (currencyItemCount < 0) {
            FCGymBadges.LOGGER.error("Invalid value set to gymBadgeCreatePrice: {}", currencyItemCount);
            return false;

        } else {
            return true;
        }
    }

    private boolean isValidCurrencyAmount() {
        if (currencyItem == Items.AIR) {
            FCGymBadges.LOGGER.error("Invalid item set to vanillaCurrencyItem: {}", FCGymBadges.config.vanillaCurrencyItem);
            return false;

        } else {
            return true;
        }
    }

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
    public boolean hasBalance(ServerPlayerEntity player, double amount) {
        return getBalance(player) >= amount;
    }
}
