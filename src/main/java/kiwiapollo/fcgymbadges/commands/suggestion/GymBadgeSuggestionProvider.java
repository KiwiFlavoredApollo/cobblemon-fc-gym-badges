package kiwiapollo.fcgymbadges.commands.suggestion;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GymBadgeSuggestionProvider implements SuggestionProvider<ServerCommandSource> {
    private static final List<String> GYM_BADGES = Arrays.asList(
            "darkTypeGymBadge",
            "leafTypeGymBadge",
            "flyingTypeGymBadge",
            "rockTypeGymBadge"
    );

    @Override
    public CompletableFuture<Suggestions> getSuggestions(
            CommandContext<ServerCommandSource> context,
            SuggestionsBuilder builder) throws CommandSyntaxException {
        GYM_BADGES.forEach(builder::suggest);
        return builder.buildFuture();
    }
}
