package kiwiapollo.fcgymbadges;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.logging.LogUtils;
import kiwiapollo.fcgymbadges.commands.GymBadgeCommand;
import kiwiapollo.fcgymbadges.economies.Economy;
import kiwiapollo.fcgymbadges.gymbadges.GymBadge;
import kiwiapollo.fcgymbadges.utilities.Config;
import kiwiapollo.fcgymbadges.utilities.ConfigLoader;
import kiwiapollo.fcgymbadges.utilities.EconomyFactory;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FractalCoffeeGymBadges implements ModInitializer {
    public static final String NAMESPACE = "fcgymbadges";
    public static final RegistryKey<ItemGroup> GYM_BADGE_ITEM_GROUP_KEY =
            RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(NAMESPACE, "item_group"));
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final List<GymBadge> GymBadges = new ArrayList<>();
    public static final ItemGroup GYM_BADGE_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Registries.ITEM.get(Identifier.of(NAMESPACE, "fire_badge"))))
            .displayName(Text.translatable("Cobblemon FC Gym Badges"))
            .build();
    public static final Config CONFIG = ConfigLoader.load();
    public static final Economy ECONOMY = EconomyFactory.create(CONFIG.economy);

    @Override
    public void onInitialize() {
        GymBadges.add(new GymBadge("dark_badge"));
        GymBadges.add(new GymBadge("leaf_badge"));
        GymBadges.add(new GymBadge("flying_badge"));
        GymBadges.add(new GymBadge("rock_badge"));
        GymBadges.add(new GymBadge("electric_badge"));
        GymBadges.add(new GymBadge("fire_badge"));
        GymBadges.add(new GymBadge("ice_badge"));
        GymBadges.add(new GymBadge("water_badge"));
        GymBadges.add(new GymBadge("dragon_badge"));
        GymBadges.add(new GymBadge("ground_badge"));
        GymBadges.add(new GymBadge("psychic_badge"));
        GymBadges.add(new GymBadge("ghost_badge"));
        GymBadges.add(new GymBadge("wing_badge"));
        GymBadges.add(new GymBadge("unaware_badge"));

        for (GymBadge b : GymBadges) {
            Registry.register(Registries.ITEM, b.identifier, b.item);
        }

        Registry.register(Registries.ITEM_GROUP, GYM_BADGE_ITEM_GROUP_KEY, GYM_BADGE_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(GYM_BADGE_ITEM_GROUP_KEY).register(itemGroup -> {
            for (GymBadge b : GymBadges) {
                itemGroup.add(b.item);
            }
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            LiteralArgumentBuilder<ServerCommandSource> rootCommand = CommandManager.literal(NAMESPACE);

            for (GymBadge b : GymBadges) {
                rootCommand.then(new GymBadgeCommand(b));
            }

            dispatcher.register(rootCommand);
        });
    }
}
