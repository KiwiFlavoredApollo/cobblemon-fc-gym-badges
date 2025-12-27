package kiwiapollo.fcgymbadges.datagen;

import kiwiapollo.fcgymbadges.item.GymBadgeItem;
import kiwiapollo.fcgymbadges.item.LockedGymBadgeItem;
import kiwiapollo.fcgymbadges.item.ModTagRegistry;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.*;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class DataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ItemTagProvider::new);
    }

    private static class ItemTagProvider extends FabricTagProvider<Item> {
        public ItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, RegistryKeys.ITEM,  registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            GymBadgeItem.getAll().forEach(getOrCreateTagBuilder(ModTagRegistry.BADGEBOX)::add);
            LockedGymBadgeItem.getAll().forEach(getOrCreateTagBuilder(ModTagRegistry.BADGEBOX)::add);
        }
    }
}
