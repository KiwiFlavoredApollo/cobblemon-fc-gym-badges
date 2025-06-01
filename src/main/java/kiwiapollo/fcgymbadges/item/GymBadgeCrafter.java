package kiwiapollo.fcgymbadges.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GymBadgeCrafter extends Item {
    private final GymBadgeItem item;

    public GymBadgeCrafter(GymBadgeItem item) {
        super(new Item.Settings());
        this.item = item;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        if (!hasPermission(user)) {
            return TypedActionResult.fail(user.getStackInHand(hand));
        }

        user.giveItemStack(item.getItem().getDefaultStack());

        if (!user.isCreative()) {
            user.getStackInHand(hand).decrement(1);
        }

        return TypedActionResult.success(user.getStackInHand(hand));
    }

    private boolean hasPermission(PlayerEntity user) {
        return true;
    }
}