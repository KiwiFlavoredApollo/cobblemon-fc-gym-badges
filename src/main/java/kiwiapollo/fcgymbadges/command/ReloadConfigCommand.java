package kiwiapollo.fcgymbadges.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import kiwiapollo.fcgymbadges.FCGymBadges;
import kiwiapollo.fcgymbadges.config.ConfigLoader;
import kiwiapollo.fcgymbadges.economy.EconomyFactory;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ReloadConfigCommand extends LiteralArgumentBuilder<ServerCommandSource> {
    protected ReloadConfigCommand() {
        super("reload");

        this.requires(new MultiCommandSourcePredicate(String.format("%s.reload", FCGymBadges.MOD_ID)))
                .executes(this::reload);
    }

    public int reload(CommandContext<ServerCommandSource> context) {
        FCGymBadges.config = new ConfigLoader().load();
        FCGymBadges.economy = new EconomyFactory().create(FCGymBadges.config.economy);

        if (context.getSource().isExecutedByPlayer()) {
            context.getSource().getPlayer().sendMessage(Text.translatable("command.fcgymbadges.reload_config.success"));
        }

        FCGymBadges.LOGGER.info("Reloaded config");

        return Command.SINGLE_SUCCESS;
    }
}
