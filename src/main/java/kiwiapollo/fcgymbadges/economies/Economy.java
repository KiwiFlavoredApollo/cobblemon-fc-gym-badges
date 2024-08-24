package kiwiapollo.fcgymbadges.economies;

import kiwiapollo.fcgymbadges.exceptions.NotEnoughBalanceException;
import net.minecraft.server.network.ServerPlayerEntity;

public interface Economy {
    public double getBalance(ServerPlayerEntity player);
    public void addBalance(ServerPlayerEntity player, double amount);
    public void removeBalance(ServerPlayerEntity player, double amount);
    public boolean isExistEnoughBalance(ServerPlayerEntity player, double amount);
}
