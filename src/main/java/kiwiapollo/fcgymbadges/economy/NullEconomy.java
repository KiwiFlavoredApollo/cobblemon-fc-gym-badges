package kiwiapollo.fcgymbadges.economy;

import kiwiapollo.fcgymbadges.FCGymBadges;
import net.minecraft.server.network.ServerPlayerEntity;

public class NullEconomy implements Economy {
    public NullEconomy() {
        FCGymBadges.LOGGER.info("Loaded NullEconomy");
    }

    @Override
    public double getBalance(ServerPlayerEntity player) {
        return 0;
    }

    @Override
    public void addBalance(ServerPlayerEntity player, double amount) {

    }

    @Override
    public void removeBalance(ServerPlayerEntity player, double amount) {

    }

    @Override
    public boolean hasBalance(ServerPlayerEntity player, double amount) {
        return true;
    }
}
