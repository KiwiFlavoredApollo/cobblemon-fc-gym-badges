package kiwiapollo.fcgymbadges.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class LockedGymBadge extends Item {
    private final GymBadgeItem badge;

    public LockedGymBadge(GymBadgeItem badge) {
        super(new Settings().maxCount(1));
        this.badge = badge;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        if (!hasPermission(user)) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        if (!user.getStackInHand(getOtherHand(hand)).getItem().equals(MiscItem.GYM_BADGE_UNLOCKER.getItem())) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        if (!user.isCreative()) {
            user.getStackInHand(hand).decrement(1);
            user.getStackInHand(getOtherHand(hand)).decrement(1);
        }

        user.giveItemStack(badge.getItem().getDefaultStack());

        return TypedActionResult.success(user.getStackInHand(hand));
    }

    private boolean hasPermission(PlayerEntity user) {
        return true;
    }

    private Hand getOtherHand(Hand hand) {
        return switch (hand) {
            case MAIN_HAND -> Hand.OFF_HAND;
            case OFF_HAND -> Hand.MAIN_HAND;
        };
    }
}