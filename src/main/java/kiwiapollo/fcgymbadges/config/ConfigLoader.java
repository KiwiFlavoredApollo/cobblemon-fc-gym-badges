package kiwiapollo.fcgymbadges.config;

import com.google.gson.Gson;
import kiwiapollo.fcgymbadges.FCGymBadges;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ConfigLoader {
    private static final File CONFIG_DIR = new File(FabricLoader.getInstance().getConfigDir().toFile(), FCGymBadges.MOD_ID);
    private static final File CONFIG_FILE = new File(CONFIG_DIR, "config.json");
    private static final Gson GSON = new Gson();

    public Config load() {
        try {
            return loadExistingConfig();

        } catch (IllegalStateException e) {
            FCGymBadges.LOGGER.error("Failed to load config");

            copyDefaultConfig();

            return loadDefaultConfig();
        }
    }

    private void copyDefaultConfig() {
        try (InputStream defaults = getDefaultConfigInputStream()) {
            if (!CONFIG_DIR.exists()) {
                CONFIG_DIR.mkdirs();
            }

            Files.copy(defaults, CONFIG_FILE.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Config loadExistingConfig() {
        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            return GSON.fromJson(reader, Config.class);

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private Config loadDefaultConfig() {
        try (InputStreamReader reader = new InputStreamReader(getDefaultConfigInputStream())) {
            return GSON.fromJson(reader, Config.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private InputStream getDefaultConfigInputStream() {
        return ConfigLoader.class.getClassLoader().getResourceAsStream("config/defaults.json");
    }
}
