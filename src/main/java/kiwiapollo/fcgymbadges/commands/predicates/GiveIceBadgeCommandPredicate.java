package kiwiapollo.fcgymbadges.commands.predicates;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;

public class GiveIceBadgeCommandPredicate extends GymBadgeCommandPredicate {
    public GiveIceBadgeCommandPredicate() {
        super(String.format("%s.%s.%s", FractalCoffeeGymBadges.NAMESPACE, "icebadge", "give"));
    }
}
