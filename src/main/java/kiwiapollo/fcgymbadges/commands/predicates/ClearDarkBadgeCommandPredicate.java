package kiwiapollo.fcgymbadges.commands.predicates;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;

public class ClearDarkBadgeCommandPredicate extends GymBadgeCommandPredicate {
    public ClearDarkBadgeCommandPredicate() {
        super(String.format("%s.%s.%s", FractalCoffeeGymBadges.NAMESPACE, "darkbadge", "clear"));
    }
}
