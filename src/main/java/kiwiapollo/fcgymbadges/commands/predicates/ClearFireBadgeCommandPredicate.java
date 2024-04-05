package kiwiapollo.fcgymbadges.commands.predicates;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;

public class ClearFireBadgeCommandPredicate extends GymBadgeCommandPredicate {
    public ClearFireBadgeCommandPredicate() {
        super(String.format("%s.%s.%s", FractalCoffeeGymBadges.NAMESPACE, "firebadge", "clear"));
    }
}
