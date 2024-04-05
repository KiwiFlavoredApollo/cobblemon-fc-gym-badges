package kiwiapollo.fcgymbadges.commands.predicates;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;

public class ClearElectricBadgeCommandPredicate extends GymBadgeCommandPredicate {
    public ClearElectricBadgeCommandPredicate() {
        super(String.format("%s.%s.%s", FractalCoffeeGymBadges.NAMESPACE, "electricbadge", "clear"));
    }
}
