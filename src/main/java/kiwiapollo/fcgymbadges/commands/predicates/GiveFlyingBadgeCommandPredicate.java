package kiwiapollo.fcgymbadges.commands.predicates;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;

public class GiveFlyingBadgeCommandPredicate extends GymBadgeCommandPredicate {
    public GiveFlyingBadgeCommandPredicate() {
        super(String.format("%s.%s.%s", FractalCoffeeGymBadges.NAMESPACE, "flyingbadge", "give"));
    }
}
