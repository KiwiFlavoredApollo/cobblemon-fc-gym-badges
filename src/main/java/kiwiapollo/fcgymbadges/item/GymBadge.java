package kiwiapollo.fcgymbadges.item;

import kiwiapollo.fcgymbadges.FCGymBadges;
import kiwiapollo.fcgymbadges.predicate.LuckPermsPredicate;
import kiwiapollo.fcgymbadges.predicate.PermissionLevelPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

import java.util.List;
import java.util.function.Predicate;

public class GymBadge extends Item {
    public GymBadge() {
        super(new Item.Settings().maxCount(1));
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (user.getWorld().isClient()) {
            return ActionResult.PASS;
        }

        if (!(entity instanceof ServerPlayerEntity target)) {
            return ActionResult.PASS;
        }

        if (!hasPermission(user, this)) {
            return ActionResult.PASS;
        }

        if (!user.isCreative() && !hasDiamondOnTheOtherHand(user, hand)) {
            return ActionResult.PASS;
        }

        ItemStack diamond = user.getStackInHand(getOtherHand(hand));
        ItemStack badge = user.getStackInHand(hand);

        if (target.getInventory().getEmptySlot() == -1) {
            user.getWorld().playSound(null, user.getBlockPos(), SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.PLAYERS, 1.0f, 1.0f);
            return ActionResult.FAIL;
        }

        target.giveItemStack(badge.getItem().getDefaultStack());

        if (!user.isCreative()) {
            diamond.decrement(1);
        }

        user.getWorld().playSound(null, user.getBlockPos(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1.0f, 1.0f);
        return ActionResult.SUCCESS;
    }

    private boolean hasPermission(PlayerEntity user, GymBadge badge) {
        return new PermissionPredicate(badge).test(user);
    }

    private boolean hasDiamondOnTheOtherHand(PlayerEntity user, Hand hand) {
        return user.getStackInHand(getOtherHand(hand)).getItem().equals(Items.DIAMOND);
    }

    private Hand getOtherHand(Hand hand) {
        return switch (hand) {
            case MAIN_HAND -> Hand.OFF_HAND;
            case OFF_HAND -> Hand.MAIN_HAND;
        };
    }

    private String getPermissionNode() {
        return Registries.ITEM.getId(this).getPath().replace("_", "");
    }

    private static class PermissionPredicate implements Predicate<PlayerEntity> {
        private static final int OP_LEVEL = 2;

        private final LuckPermsPredicate luckperms;
        private final PermissionLevelPredicate level;

        public PermissionPredicate(GymBadge badge) {
            this.luckperms = new LuckPermsPredicate(getPermissionNodes(badge));
            this.level = new PermissionLevelPredicate(OP_LEVEL);
        }

        private List<String> getPermissionNodes(GymBadge badge) {
            return List.of(
                    String.format("%s.%s", FCGymBadges.MOD_ID, badge.getPermissionNode())
            );
        }

        @Override
        public boolean test(PlayerEntity player) {
            return luckperms.test(player) || level.test(player);
        }
    }
}