package kiwiapollo.fcgymbadges.economy;

public class EconomyFactory {
    public Economy create(String identifier) {
        try {
            return switch (identifier) {
                case "Vanilla" -> new VanillaEconomy();
                default -> new NullEconomy();
            };

        } catch (IllegalStateException e) {
            return new NullEconomy();
        }
    }
}
