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

public class GymBadgeCase {
    private final UUID uuid;
    private final JsonObject gymBadges;

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
            JsonObject jsonObject = readJsonFile();
            assertNotLegacyJsonFile(jsonObject);
            return jsonObject;
        } catch (LegacyJsonFileException e) {
            FractalCoffeeGymBadges.LOGGER.debug("Loaded gym badges from legacy json file");
            return readFromLegacy(e.getJsonObject());
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

    private JsonObject readFromLegacy(JsonObject jsonObject) {
        JsonObject newJsonObject = new JsonObject();
        newJsonObject.addProperty(getDarkBadgeCamelCase(), jsonObject.get("darkTypeGymBadge").getAsBoolean());
        newJsonObject.addProperty(getLeafBadgeCamelCase(), jsonObject.get("leafTypeGymBadge").getAsBoolean());
        newJsonObject.addProperty(getFlyingBadgeCamelCase(), jsonObject.get("flyingTypeGymBadge").getAsBoolean());
        newJsonObject.addProperty(getRockBadgeCamelCase(), jsonObject.get("rockTypeGymBadge").getAsBoolean());
        return newJsonObject;
    }

    private JsonObject readJsonFile() throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader fileReader = new FileReader(getFilePath());
        return gson.fromJson(fileReader, JsonObject.class);
    }

    private JsonObject loadGymBadgesFromDefault() {
        JsonObject jsonObject =  new JsonObject();
        jsonObject.addProperty(getDarkBadgeCamelCase(), false);
        jsonObject.addProperty(getLeafBadgeCamelCase(), false);
        jsonObject.addProperty(getFlyingBadgeCamelCase(), false);
        jsonObject.addProperty(getRockBadgeCamelCase(), false);
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

    private String getDarkBadgeCamelCase() {
        return FractalCoffeeGymBadges.DARK_BADGE.getNameCamelCase();
    }

    public boolean isExistDarkBadge() {
        return gymBadges.get(getDarkBadgeCamelCase()).getAsBoolean();
    }

    public void addDarkBadge() {
        gymBadges.addProperty(getDarkBadgeCamelCase(), true);
    }

    public void removeDarkBadge() {
        gymBadges.addProperty(getDarkBadgeCamelCase(), false);
    }

    private String getLeafBadgeCamelCase() {
        return FractalCoffeeGymBadges.LEAF_BADGE.getNameCamelCase();
    }

    public boolean isExistLeafBadge() {
        return gymBadges.get(getLeafBadgeCamelCase()).getAsBoolean();
    }

    public void addLeafBadge() {
        gymBadges.addProperty(getLeafBadgeCamelCase(), true);
    }

    public void removeLeafBadge() {
        gymBadges.addProperty(getLeafBadgeCamelCase(), false);
    }

    private String getFlyingBadgeCamelCase() {
        return FractalCoffeeGymBadges.FLYING_BADGE.getNameCamelCase();
    }

    public boolean isExistFlyingBadge() {
        return gymBadges.get(getFlyingBadgeCamelCase()).getAsBoolean();
    }

    public void addFlyingBadge() {
        gymBadges.addProperty(getFlyingBadgeCamelCase(), true);
    }

    public void removeFlyingBadge() {
        gymBadges.addProperty(getFlyingBadgeCamelCase(), false);
    }

    private String getRockBadgeCamelCase() {
        return FractalCoffeeGymBadges.ROCK_BADGE.getNameCamelCase();
    }

    public boolean isExistRockBadge() {
        return gymBadges.get(getRockBadgeCamelCase()).getAsBoolean();
    }

    public void addRockBadge() {
        gymBadges.addProperty(getRockBadgeCamelCase(), true);
    }

    public void removeRockBadge() {
        gymBadges.addProperty(getRockBadgeCamelCase(), false);
    }
}
