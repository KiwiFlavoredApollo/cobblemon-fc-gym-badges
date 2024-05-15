package kiwiapollo.fcgymbadges.commands.predicates;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;

public class ClearIceBadgeCommandPredicate extends GymBadgeCommandPredicate {
    public ClearIceBadgeCommandPredicate() {
        super(String.format("%s.%s.%s", FractalCoffeeGymBadges.NAMESPACE, "icebadge", "clear"));
    }
}
