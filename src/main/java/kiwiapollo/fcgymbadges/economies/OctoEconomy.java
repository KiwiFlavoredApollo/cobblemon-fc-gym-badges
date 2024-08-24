package kiwiapollo.fcgymbadges.economies;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import kiwiapollo.fcgymbadges.exceptions.EconomyNotLoadedException;
import net.minecraft.server.network.ServerPlayerEntity;

public class OctoEconomy implements Economy {
    public OctoEconomy() throws EconomyNotLoadedException {
        try {
            Class.forName("com.epherical.octoecon.OctoEconomy");
            FractalCoffeeGymBadges.LOGGER.info("Loaded OctoEconomy");

        } catch (ClassNotFoundException e) {
            throw new EconomyNotLoadedException();
        }
    }

    @Override
    public double getBalance(ServerPlayerEntity player) {
        com.epherical.octoecon.OctoEconomy octoEconomy = com.epherical.octoecon.OctoEconomy.getInstance();
        return octoEconomy.getCurrentEconomy().getOrCreatePlayerAccount(player.getUuid())
                .getBalance(octoEconomy.getCurrentEconomy().getDefaultCurrency());
    }

    @Override
    public void addBalance(ServerPlayerEntity player, double amount) {
        com.epherical.octoecon.OctoEconomy octoEconomy = com.epherical.octoecon.OctoEconomy.getInstance();
        octoEconomy.getCurrentEconomy().getOrCreatePlayerAccount(player.getUuid())
                .depositMoney(octoEconomy.getCurrentEconomy().getDefaultCurrency(), amount, "");
    }

    @Override
    public void removeBalance(ServerPlayerEntity player, double amount) {
        com.epherical.octoecon.OctoEconomy octoEconomy = com.epherical.octoecon.OctoEconomy.getInstance();
        octoEconomy.getCurrentEconomy().getOrCreatePlayerAccount(player.getUuid())
                .withdrawMoney(octoEconomy.getCurrentEconomy().getDefaultCurrency(), amount, "");
    }

    @Override
    public boolean isExistEnoughBalance(ServerPlayerEntity player, double amount) {
        com.epherical.octoecon.OctoEconomy octoEconomy = com.epherical.octoecon.OctoEconomy.getInstance();
        return octoEconomy.getCurrentEconomy().getOrCreatePlayerAccount(player.getUuid())
                .hasAmount(octoEconomy.getCurrentEconomy().getDefaultCurrency(), amount);
    }
}
