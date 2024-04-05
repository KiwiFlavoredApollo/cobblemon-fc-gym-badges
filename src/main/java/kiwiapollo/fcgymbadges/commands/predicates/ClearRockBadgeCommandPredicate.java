package kiwiapollo.fcgymbadges.commands.predicates;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import kiwiapollo.fcgymbadges.commands.ClearGymBadgeCommand;

public class ClearRockBadgeCommandPredicate extends GymBadgeCommandPredicate {
    public ClearRockBadgeCommandPredicate() {
        super(String.format("%s.%s.%s", FractalCoffeeGymBadges.NAMESPACE, "rockbadge", "clear"));
    }
}
