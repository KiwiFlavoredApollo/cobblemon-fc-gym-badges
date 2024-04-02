package kiwiapollo.fcgymbadges.storage;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import kiwiapollo.fcgymbadges.exceptions.JsonFileReadErrorException;
import kiwiapollo.fcgymbadges.exceptions.JsonFileWriteErrorException;
import net.minecraft.server.network.ServerPlayerEntity;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class GymBadgeCase implements JsonFile {
    private final UUID uuid;
    private JsonObject gymBadges;

    @Override
    public void load() {
        this.gymBadges = loadGymBadgesJsonObject();
    }

    @Override
    public void save() {
        saveGymBadgesJsonObject();
    }

    public GymBadgeCase(ServerPlayerEntity player) {
        this.uuid = player.getUuid();
        this.gymBadges = loadGymBadgesJsonObject();
    }

    private JsonObject loadGymBadgesJsonObject() {
        try {
            return loadGymBadgesFromFile();
        } catch (JsonFileReadErrorException e) {
            FractalCoffeeGymBadges.LOGGER.debug(e.getMessage());
            return loadGymBadgesFromDefault();
        }
    }

    private JsonObject loadGymBadgesFromFile() throws JsonFileReadErrorException {
        try {
            return readJsonFile();
        } catch (FileNotFoundException e) {
            throw new JsonFileReadErrorException("Failed to read json file");
        }
    }

    private JsonObject readJsonFile() throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader fileReader = new FileReader(getFilePath());
        return gson.fromJson(fileReader, JsonObject.class);
    }

    private JsonObject loadGymBadgesFromDefault() {
        JsonObject jsonObject =  new JsonObject();

        jsonObject.addProperty("darkTypeGymBadge", false);
        jsonObject.addProperty("leafTypeGymBadge", false);
        jsonObject.addProperty("flyingTypeGymBadge", false);
        jsonObject.addProperty("rockTypeGymBadge", false);

        return jsonObject;
    }

    private void saveGymBadgesJsonObject() {
        try {
            saveGymBadgesToFile();
        } catch (JsonFileWriteErrorException e) {
            FractalCoffeeGymBadges.LOGGER.debug(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void saveGymBadgesToFile() throws JsonFileWriteErrorException {
        try {
            writeJsonFile();
        } catch (IOException e) {
            throw new JsonFileWriteErrorException("Failed to write json file");
        }
    }

    private void writeJsonFile() throws IOException {
        assertExistParentDirectory();

        Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter(getFilePath());
        gson.toJson(gymBadges, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }

    private void assertExistParentDirectory() throws IOException {
        Files.createDirectories(Path.of(getFilePath()).getParent());
    }

    private String getFileName() {
        String jsonFileExtension = ".json";
        return uuid.toString() + jsonFileExtension;
    }

    private String getFilePath() {
        String config = "config";
        return Paths.get(config, FractalCoffeeGymBadges.NAMESPACE, getFileName()).toString();
    }

    public void addGymBadge(String gymBadge) {
        gymBadges.addProperty(gymBadge, true);
    }

    public void removeGymBadge(String gymBadge) {
        gymBadges.addProperty(gymBadge, false);
    }

    public boolean isExistGymBadge(String gymBadge) {
        return gymBadges.get(gymBadge).getAsBoolean();
    }
}
