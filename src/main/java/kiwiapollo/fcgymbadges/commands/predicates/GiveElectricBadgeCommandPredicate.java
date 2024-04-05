package kiwiapollo.fcgymbadges.commands.predicates;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;

public class GiveElectricBadgeCommandPredicate extends GymBadgeCommandPredicate {
    public GiveElectricBadgeCommandPredicate() {
        super(String.format("%s.%s.%s", FractalCoffeeGymBadges.NAMESPACE, "electricbadge", "give"));
    }
}
