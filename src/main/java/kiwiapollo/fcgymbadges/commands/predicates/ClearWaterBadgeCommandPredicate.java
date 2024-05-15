package kiwiapollo.fcgymbadges.commands.predicates;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;

public class ClearWaterBadgeCommandPredicate extends GymBadgeCommandPredicate {
    public ClearWaterBadgeCommandPredicate() {
        super(String.format("%s.%s.%s", FractalCoffeeGymBadges.NAMESPACE, "waterbadge", "clear"));
    }
}
