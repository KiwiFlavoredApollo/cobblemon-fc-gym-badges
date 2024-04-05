package kiwiapollo.fcgymbadges.commands.predicates;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;

public class GiveFireBadgeCommandPredicate extends GymBadgeCommandPredicate {
    public GiveFireBadgeCommandPredicate() {
        super(String.format("%s.%s.%s", FractalCoffeeGymBadges.NAMESPACE, "firebadge", "give"));
    }
}
