package kiwiapollo.fcgymbadges.utilities;

import com.google.gson.Gson;
import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import kiwiapollo.fcgymbadges.exceptions.LoadingConfigFailedException;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ConfigLoader {
    private static final File CONFIG_DIR =
            new File(FabricLoader.getInstance().getConfigDir().toFile(), FractalCoffeeGymBadges.NAMESPACE);
    private static final File CONFIG_FILE = new File(CONFIG_DIR, "config.json");
    private static final InputStream DEFAULT_CONFIG_FILE =
        FractalCoffeeGymBadges.class.getClassLoader().getResourceAsStream("config/defaults.json");
    private static final Gson GSON = new Gson();

    public static Config load() {
        try {
            Config config = loadExistingConfig();
            assertSuccessLoadingConfig(config);
            return config;

        } catch (LoadingConfigFailedException e) {
            copyDefaultConfig();
            return loadDefaultConfig();
        }
    }

    private static void copyDefaultConfig() {
        try {
            Files.copy(DEFAULT_CONFIG_FILE, CONFIG_FILE.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Config loadExistingConfig() throws LoadingConfigFailedException {
        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            return GSON.fromJson(reader, Config.class);
        } catch (IOException e) {
            throw new LoadingConfigFailedException();
        }
    }

    private static Config loadDefaultConfig() {
        try (InputStreamReader reader = new InputStreamReader(DEFAULT_CONFIG_FILE)) {
            return GSON.fromJson(reader, Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void assertSuccessLoadingConfig(Config config) throws LoadingConfigFailedException {
        if (config == null) {
           throw new LoadingConfigFailedException();
        }
    }
}
