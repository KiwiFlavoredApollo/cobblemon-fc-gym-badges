package kiwiapollo.fcgymbadges.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Arrays;

public class GymBadgeUnlocker extends Item {
    public GymBadgeUnlocker() {
        super(new Settings().maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        if (!hasPermission(user)) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        if (!hasLockedGymBadgeInOtherHand(user, hand)) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        LockedGymBadge locked = (LockedGymBadge) user.getStackInHand(getOtherHand(hand)).getItem();
        user.giveItemStack(locked.getGymBadge().getDefaultStack());

        if (!user.isCreative()) {
            user.getStackInHand(hand).decrement(1);
            user.getStackInHand(getOtherHand(hand)).decrement(1);
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }

    private boolean hasPermission(PlayerEntity user) {
        return true;
    }

    private boolean hasLockedGymBadgeInOtherHand(PlayerEntity user, Hand hand) {
        Item item = user.getStackInHand(getOtherHand(hand)).getItem();

        return Arrays.stream(LockedGymBadgeItem.values())
                .map(LockedGymBadgeItem::getItem)
                .noneMatch(item::equals);
    }

    private Hand getOtherHand(Hand hand) {
        return switch (hand) {
            case MAIN_HAND -> Hand.OFF_HAND;
            case OFF_HAND -> Hand.MAIN_HAND;
        };
    }
}