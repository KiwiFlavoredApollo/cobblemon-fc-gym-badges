package kiwiapollo.fcgymbadges.commands.predicates;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;

public class GiveRockBadgeCommandPredicate extends GymBadgeCommandPredicate {
    public GiveRockBadgeCommandPredicate() {
        super(String.format("%s.%s.%s", FractalCoffeeGymBadges.NAMESPACE, "rockbadge", "give"));
    }
}
