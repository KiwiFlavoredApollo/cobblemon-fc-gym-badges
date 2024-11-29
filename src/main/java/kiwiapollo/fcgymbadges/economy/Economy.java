package kiwiapollo.fcgymbadges.economy;

import net.minecraft.server.network.ServerPlayerEntity;

public interface Economy {
    double getBalance(ServerPlayerEntity player);

    void addBalance(ServerPlayerEntity player, double amount);

    void removeBalance(ServerPlayerEntity player, double amount);

    boolean hasBalance(ServerPlayerEntity player, double amount);
}
