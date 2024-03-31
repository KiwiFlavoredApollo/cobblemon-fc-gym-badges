package kiwiapollo.fcgymbadges;

import net.fabricmc.api.ModInitializer;

public class FractalCoffeeGymBadges implements ModInitializer {

    public static final String NAMESPACE = "fcgymbadges";
    public static final GymBadge darkTypeGymBadge = new DarkTypeGymBadge();
    public static final GymBadge leafTypeGymBadge = new LeafTypeGymBadge();
    public static final GymBadge flyingTypeGymBadge = new FlyingTypeGymBadge();
    public static final GymBadgeItemGroup gymBadgeItemGroup = new GymBadgeItemGroup();

    @Override
    public void onInitialize() {
        gymBadgeItemGroup.addGymBadge(darkTypeGymBadge);
        gymBadgeItemGroup.addGymBadge(leafTypeGymBadge);
        gymBadgeItemGroup.addGymBadge(flyingTypeGymBadge);
    }
}
