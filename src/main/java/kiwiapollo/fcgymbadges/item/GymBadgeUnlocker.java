package kiwiapollo.fcgymbadges.item;

import kiwiapollo.fcgymbadges.FCGymBadges;
import kiwiapollo.fcgymbadges.predicate.LuckPermsPredicate;
import kiwiapollo.fcgymbadges.predicate.PermissionLevelPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class GymBadgeUnlocker extends Item {
    public GymBadgeUnlocker() {
        super(new Settings().maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        if (!hasLockedGymBadgeInOtherHand(user, hand)) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        ItemStack locked = user.getStackInHand(getOtherHand(hand));
        ItemStack unlocker = user.getStackInHand(hand);

        if (!hasPermission(user, (LockedGymBadge) locked.getItem())) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        Item badge = ((LockedGymBadge) locked.getItem()).getGymBadge();
        user.giveItemStack(badge.getDefaultStack());

        if (!user.isCreative()) {
            locked.decrement(1);
            unlocker.decrement(1);
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }

    private boolean hasPermission(PlayerEntity user, LockedGymBadge locked) {
        return new PermissionPredicateFactory(locked).create().test(user);
    }

    private boolean hasLockedGymBadgeInOtherHand(PlayerEntity user, Hand hand) {
        Item item = user.getStackInHand(getOtherHand(hand)).getItem();

        return Arrays.stream(LockedGymBadgeItem.values())
                .map(LockedGymBadgeItem::getItem)
                .anyMatch(item::equals);
    }

    private Hand getOtherHand(Hand hand) {
        return switch (hand) {
            case MAIN_HAND -> Hand.OFF_HAND;
            case OFF_HAND -> Hand.MAIN_HAND;
        };
    }

    private static class PermissionPredicateFactory implements SimpleFactory<Predicate<PlayerEntity>> {
        private static final int OP_LEVEL = 2;

        private final LockedGymBadge locked;

        public PermissionPredicateFactory(LockedGymBadge locked) {
            this.locked = locked;
        }

        @Override
        public Predicate<PlayerEntity> create() {
            return new LuckPermsPredicate(getPermissionNodes()).or(new PermissionLevelPredicate(OP_LEVEL));
        }

        private List<String> getPermissionNodes() {
            return List.of(
                    String.format("%s.%s.unlock", FCGymBadges.MOD_ID, locked.getPermissionNode())
            );
        }
    }
}