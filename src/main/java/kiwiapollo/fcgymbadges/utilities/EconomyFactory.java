package kiwiapollo.fcgymbadges.utilities;

import kiwiapollo.fcgymbadges.economies.Economy;
import kiwiapollo.fcgymbadges.economies.NullEconomy;
import kiwiapollo.fcgymbadges.economies.OctoEconomy;
import kiwiapollo.fcgymbadges.economies.Vanilla;
import kiwiapollo.fcgymbadges.exceptions.EconomyNotLoadedException;

public class EconomyFactory {
    public static Economy create(String identifier) {
        try {
            return switch (identifier) {
                case "Vanilla" -> new Vanilla();
                case "OctoEconomy" -> new OctoEconomy();
                default -> new NullEconomy();
            };

        } catch (EconomyNotLoadedException e) {
            return new NullEconomy();
        }
    }
}
