package kiwiapollo.fcgymbadges.commands.predicates;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;

public class GiveWaterBadgeCommandPredicate extends GymBadgeCommandPredicate {
    public GiveWaterBadgeCommandPredicate() {
        super(String.format("%s.%s.%s", FractalCoffeeGymBadges.NAMESPACE, "waterbadge", "give"));
    }
}
