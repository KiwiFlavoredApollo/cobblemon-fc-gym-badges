package kiwiapollo.fcgymbadges.gymbadges;

import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GymBadge {
    private final Item item;
    private final Identifier identifier;

    public GymBadge(String itemName) {
        this.item = new Item(new FabricItemSettings());
        this.identifier = new Identifier(FractalCoffeeGymBadges.NAMESPACE, itemName);

        registerItem();
    }

    private void registerItem() {
        Registry.register(Registries.ITEM, identifier, item);
    }

    public Item getItem() {
        return item;
    }

    public String getNameSnakeCase() {
        return identifier.getPath();
    }

    public String getNameCamelCase() {
        return snakeToCamel(getNameSnakeCase());
    }

    private String snakeToCamel(String snakeCase) {
        Pattern pattern = Pattern.compile("([a-z])_([a-z])");
        Matcher matcher = pattern.matcher(snakeCase.toLowerCase());

        StringBuilder stringBuilder = new StringBuilder();
        while(matcher.find()) {
            matcher.appendReplacement(stringBuilder,
                    matcher.group(1) + matcher.group(2).toUpperCase());
        }
        matcher.appendTail(stringBuilder);

        return stringBuilder.toString();
    }
}
