package kiwiapollo.fcgymbadges.mixin;

import kiwiapollo.fcgymbadges.FCGymBadges;
import kiwiapollo.fcgymbadges.item.CustomItem;
import kiwiapollo.fcgymbadges.item.GymBadge;
import kiwiapollo.fcgymbadges.predicate.LuckPermsPredicate;
import kiwiapollo.fcgymbadges.predicate.CreativeLevelOpPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Slot.class)
public abstract class SlotMixin {
    @Shadow
    public abstract ItemStack getStack();

    @Inject(method = "canTakeItems", at = @At("HEAD"), cancellable = true)
    public void allowCraftingBadgesOnlyForThoseWithPermission(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (!isCraftingResultSlot((Slot) (Object) this)) {
            return;
        }

        ItemStack stack = this.getStack();

        if (!isBadgeItem(stack)) {
            return;
        }

        GymBadge badge = (GymBadge) stack.getItem();

        if (hasPermission(player, badge)) {
            return;
        }

        player.playSound(SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.PLAYERS, 1.0f, 1.0f);
        player.sendMessage(Text.translatable("item.fcgymbadges.error.no_permission").formatted(Formatting.RED));

        cir.setReturnValue(false);
        cir.cancel();
    }

    private boolean isCraftingResultSlot(Slot slot) {
        return slot instanceof CraftingResultSlot;
    }

    private boolean isBadgeItem(ItemStack stack) {
        return CustomItem.getAll().stream().anyMatch(stack::isOf);
    }

    private boolean hasPermission(PlayerEntity player, GymBadge badge) {
        LuckPermsPredicate luckperms = new LuckPermsPredicate(getPermissionNodes(badge));
        CreativeLevelOpPredicate level = new CreativeLevelOpPredicate();

        return luckperms.test(player) || level.test(player);
    }

    private List<String> getPermissionNodes(GymBadge badge) {
        return List.of(
                String.format("%s.%s", FCGymBadges.MOD_ID, getPermissionNode(badge))
        );
    }

    private String getPermissionNode(GymBadge badge) {
        return Registries.ITEM.getId(badge).getPath().replace("_", "");
    }
}
