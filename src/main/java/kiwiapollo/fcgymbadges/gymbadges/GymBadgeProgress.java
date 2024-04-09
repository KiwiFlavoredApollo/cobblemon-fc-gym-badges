package kiwiapollo.fcgymbadges.gymbadges;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import kiwiapollo.fcgymbadges.FractalCoffeeGymBadges;
import kiwiapollo.fcgymbadges.exceptions.JsonFileReadErrorException;
import kiwiapollo.fcgymbadges.exceptions.JsonFileWriteErrorException;
import kiwiapollo.fcgymbadges.exceptions.LegacyJsonFileException;
import net.minecraft.server.network.ServerPlayerEntity;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class GymBadgeProgress {
    private final UUID uuid;
    private final JsonObject gymBadges;

    public void save() {
        saveGymBadgesJsonObject();
    }

    public GymBadgeProgress(ServerPlayerEntity player) {
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
            JsonObject jsonObject = readJsonFile();
            assertNotLegacyJsonFile(jsonObject);
            return jsonObject;
        } catch (LegacyJsonFileException e) {
            FractalCoffeeGymBadges.LOGGER.debug("Loaded gym badges from legacy json file");
            return legacyToCurrent(e.getJsonObject());
        } catch (FileNotFoundException e) {
            throw new JsonFileReadErrorException("Failed to read json file");
        }
    }

    private void assertNotLegacyJsonFile(JsonObject jsonObject) throws LegacyJsonFileException {
        if(jsonObject.has("darkTypeGymBadge") ||
                jsonObject.has("leafTypeGymBadge") ||
                jsonObject.has("flyingTypeGymBadge") ||
                jsonObject.has("rockTypeGymBadge")) {
            throw new LegacyJsonFileException(jsonObject);
        }
    }

    private JsonObject legacyToCurrent(JsonObject jsonObject) {
        JsonObject newJsonObject = new JsonObject();
        newJsonObject.addProperty(
                FractalCoffeeGymBadges.DARK_BADGE.getNameCamelCase(),
                jsonObject.get("darkTypeGymBadge").getAsBoolean());
        newJsonObject.addProperty(
                FractalCoffeeGymBadges.LEAF_BADGE.getNameCamelCase(),
                jsonObject.get("leafTypeGymBadge").getAsBoolean());
        newJsonObject.addProperty(
                FractalCoffeeGymBadges.FLYING_BADGE.getNameCamelCase(),
                jsonObject.get("flyingTypeGymBadge").getAsBoolean());
        newJsonObject.addProperty(
                FractalCoffeeGymBadges.ROCK_BADGE.getNameCamelCase(),
                jsonObject.get("rockTypeGymBadge").getAsBoolean());
        return newJsonObject;
    }

    private JsonObject readJsonFile() throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader fileReader = new FileReader(getFilePath());
        return gson.fromJson(fileReader, JsonObject.class);
    }

    private JsonObject loadGymBadgesFromDefault() {
        JsonObject jsonObject =  new JsonObject();
        setFalseGymBadgeProperty(jsonObject, FractalCoffeeGymBadges.DARK_BADGE);
        setFalseGymBadgeProperty(jsonObject, FractalCoffeeGymBadges.LEAF_BADGE);
        setFalseGymBadgeProperty(jsonObject, FractalCoffeeGymBadges.FLYING_BADGE);
        setFalseGymBadgeProperty(jsonObject, FractalCoffeeGymBadges.ROCK_BADGE);
        setFalseGymBadgeProperty(jsonObject, FractalCoffeeGymBadges.ELECTRIC_BADGE);
        setFalseGymBadgeProperty(jsonObject, FractalCoffeeGymBadges.FIRE_BADGE);
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

    public boolean isExistGymBadge(GymBadge gymBadge) {
        String name = gymBadge.getNameCamelCase();
        return gymBadges.get(name).getAsBoolean();
    }

    public void addGymBadge(GymBadge gymBadge) {
        setTrueGymBadgeProperty(gymBadges, gymBadge);
    }

    public void removeGymBadge(GymBadge gymBadge) {
        setFalseGymBadgeProperty(gymBadges, gymBadge);
    }

    private void setTrueGymBadgeProperty(JsonObject jsonObject, GymBadge gymBadge) {
        String name = gymBadge.getNameCamelCase();
        jsonObject.addProperty(name, true);
    }

    private void setFalseGymBadgeProperty(JsonObject jsonObject, GymBadge gymBadge) {
        String name = gymBadge.getNameCamelCase();
        jsonObject.addProperty(name, false);
    }
}
