package kiwiapollo.fcgymbadges.economy;

import kiwiapollo.fcgymbadges.exception.EconomyLoadFailedException;

public class EconomyFactory {
    public Economy create(String identifier) {
        try {
            return switch (identifier) {
                case "Vanilla" -> new VanillaEconomy();
                default -> new NullEconomy();
            };

        } catch (EconomyLoadFailedException e) {
            return new NullEconomy();
        }
    }
}
